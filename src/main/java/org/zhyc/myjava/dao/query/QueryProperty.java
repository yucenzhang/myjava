package org.zhyc.myjava.dao.query;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryProperty<T> {
	
	public abstract Class<T> getEntityClass();
	public abstract Map<String, Object> getValues();
	
	protected static final Map<String, Map<String, String>> databaseSpecificStatements = new HashMap<String, Map<String, String>>();

	public static final Map<String, String> databaseSpecificLimitBeforeStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseSpecificLimitAfterStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseSpecificLimitBetweenStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseSpecificOrderByStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseOuterJoinLimitBetweenStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseSpecificLimitBeforeNativeQueryStatements = new HashMap<String, String>();

	static {

		String defaultOrderBy = " order by ${orderBy} ";

		// h2
		databaseSpecificLimitBeforeStatements.put("h2", "");
		databaseSpecificLimitAfterStatements.put("h2", "LIMIT #{maxResults} OFFSET #{firstResult}");
		databaseSpecificLimitBetweenStatements.put("h2", "");
		databaseOuterJoinLimitBetweenStatements.put("h2", "");
		databaseSpecificOrderByStatements.put("h2", defaultOrderBy);

		// mysql specific
		databaseSpecificLimitBeforeStatements.put("mysql", "");
		databaseSpecificLimitAfterStatements.put("mysql", "LIMIT #{maxResults} OFFSET #{firstResult}");
		databaseSpecificLimitBetweenStatements.put("mysql", "");
		databaseOuterJoinLimitBetweenStatements.put("mysql", "");
		databaseSpecificOrderByStatements.put("mysql", defaultOrderBy);
		addDatabaseSpecificStatement("mysql", "selectNextJobsToExecute", "selectNextJobsToExecute_mysql");
		addDatabaseSpecificStatement("mysql", "selectExclusiveJobsToExecute", "selectExclusiveJobsToExecute_mysql");
		addDatabaseSpecificStatement("mysql", "selectProcessDefinitionsByQueryCriteria", "selectProcessDefinitionsByQueryCriteria_mysql");
		addDatabaseSpecificStatement("mysql", "selectProcessDefinitionCountByQueryCriteria", "selectProcessDefinitionCountByQueryCriteria_mysql");
		addDatabaseSpecificStatement("mysql", "selectDeploymentsByQueryCriteria", "selectDeploymentsByQueryCriteria_mysql");
		addDatabaseSpecificStatement("mysql", "selectDeploymentCountByQueryCriteria", "selectDeploymentCountByQueryCriteria_mysql");
		addDatabaseSpecificStatement("mysql", "selectModelCountByQueryCriteria", "selectModelCountByQueryCriteria_mysql");
		addDatabaseSpecificStatement("mysql", "updateExecutionTenantIdForDeployment", "updateExecutionTenantIdForDeployment_mysql");
		addDatabaseSpecificStatement("mysql", "updateTaskTenantIdForDeployment", "updateTaskTenantIdForDeployment_mysql");
		addDatabaseSpecificStatement("mysql", "updateJobTenantIdForDeployment", "updateJobTenantIdForDeployment_mysql");

		// postgres specific
		databaseSpecificLimitBeforeStatements.put("postgres", "");
		databaseSpecificLimitAfterStatements.put("postgres",
				"LIMIT #{maxResults} OFFSET #{firstResult}");
		databaseSpecificLimitBetweenStatements.put("postgres", "");
		databaseOuterJoinLimitBetweenStatements.put("postgres", "");
		databaseSpecificOrderByStatements.put("postgres", defaultOrderBy);
		addDatabaseSpecificStatement("postgres", "insertByteArray", "insertByteArray_postgres");
		addDatabaseSpecificStatement("postgres", "updateByteArray", "updateByteArray_postgres");
		addDatabaseSpecificStatement("postgres", "selectByteArray", "selectByteArray_postgres");
		addDatabaseSpecificStatement("postgres", "selectResourceByDeploymentIdAndResourceName", "selectResourceByDeploymentIdAndResourceName_postgres");
		addDatabaseSpecificStatement("postgres", "selectResourcesByDeploymentId", "selectResourcesByDeploymentId_postgres");
		addDatabaseSpecificStatement("postgres", "insertIdentityInfo", "insertIdentityInfo_postgres");
		addDatabaseSpecificStatement("postgres", "updateIdentityInfo", "updateIdentityInfo_postgres");
		addDatabaseSpecificStatement("postgres", "selectIdentityInfoById", "selectIdentityInfoById_postgres");
		addDatabaseSpecificStatement("postgres", "selectIdentityInfoByUserIdAndKey", "selectIdentityInfoByUserIdAndKey_postgres");
		addDatabaseSpecificStatement("postgres", "selectIdentityInfoByUserId", "selectIdentityInfoByUserId_postgres");
		addDatabaseSpecificStatement("postgres", "selectIdentityInfoDetails", "selectIdentityInfoDetails_postgres");
		addDatabaseSpecificStatement("postgres", "insertComment", "insertComment_postgres");
		addDatabaseSpecificStatement("postgres", "selectComment", "selectComment_postgres");
		addDatabaseSpecificStatement("postgres", "selectCommentsByTaskId", "selectCommentsByTaskId_postgres");
		addDatabaseSpecificStatement("postgres", "selectCommentsByProcessInstanceId", "selectCommentsByProcessInstanceId_postgres");
		addDatabaseSpecificStatement("postgres", "selectCommentsByType", "selectCommentsByType_postgres");
		addDatabaseSpecificStatement("postgres", "selectCommentsByTaskIdAndType", "selectCommentsByTaskIdAndType_postgres");
		addDatabaseSpecificStatement("postgres", "selectEventsByTaskId", "selectEventsByTaskId_postgres");

		// oracle
		databaseSpecificLimitBeforeStatements.put("oracle", "select * from ( select a.*, ROWNUM rnum from (");
		databaseSpecificLimitAfterStatements.put("oracle", "  ) a where ROWNUM < #{lastRow}) where rnum  >= #{firstRow}");
		databaseSpecificLimitBetweenStatements.put("oracle", "");
		databaseOuterJoinLimitBetweenStatements.put("oracle", "");
		databaseSpecificOrderByStatements.put("oracle", defaultOrderBy);
		addDatabaseSpecificStatement("oracle", "selectExclusiveJobsToExecute", "selectExclusiveJobsToExecute_integerBoolean");

		// db2
		databaseSpecificLimitBeforeStatements.put("db2", "SELECT SUB.* FROM (");
		databaseSpecificLimitAfterStatements.put("db2", ")RES ) SUB WHERE SUB.rnk >= #{firstRow} AND SUB.rnk < #{lastRow}");
		databaseSpecificLimitBetweenStatements.put("db2", ", row_number() over (ORDER BY ${orderBy}) rnk FROM ( select distinct RES.* ");
		databaseOuterJoinLimitBetweenStatements.put("db2", ", row_number() over (ORDER BY ${mssqlOrDB2OrderBy}) rnk FROM ( select distinct ");
		databaseSpecificOrderByStatements.put("db2", "");
		databaseSpecificLimitBeforeNativeQueryStatements.put("db2", "SELECT SUB.* FROM ( select RES.* , row_number() over (ORDER BY ${orderBy}) rnk FROM (");
		addDatabaseSpecificStatement("db2", "selectExclusiveJobsToExecute", "selectExclusiveJobsToExecute_integerBoolean");
		addDatabaseSpecificStatement("db2", "selectExecutionByNativeQuery", "selectExecutionByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricActivityInstanceByNativeQuery", "selectHistoricActivityInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricProcessInstanceByNativeQuery", "selectHistoricProcessInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricTaskInstanceByNativeQuery", "selectHistoricTaskInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectTaskByNativeQuery", "selectTaskByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectProcessDefinitionByNativeQuery", "selectProcessDefinitionByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectDeploymentByNativeQuery", "selectDeploymentByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectGroupByNativeQuery", "selectGroupByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectUserByNativeQuery", "selectUserByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectModelByNativeQuery", "selectModelByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricDetailByNativeQuery", "selectHistoricDetailByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricVariableInstanceByNativeQuery", "selectHistoricVariableInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectTaskWithVariablesByQueryCriteria", "selectTaskWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectProcessInstanceWithVariablesByQueryCriteria", "selectProcessInstanceWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricProcessInstancesWithVariablesByQueryCriteria", "selectHistoricProcessInstancesWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("db2", "selectHistoricTaskInstancesWithVariablesByQueryCriteria", "selectHistoricTaskInstancesWithVariablesByQueryCriteria_mssql_or_db2");

		// mssql
		databaseSpecificLimitBeforeStatements.put("mssql", "SELECT SUB.* FROM (");
		databaseSpecificLimitAfterStatements.put("mssql", ")RES ) SUB WHERE SUB.rnk >= #{firstRow} AND SUB.rnk < #{lastRow}");
		databaseSpecificLimitBetweenStatements.put("mssql", ", row_number() over (ORDER BY ${orderBy}) rnk FROM ( select distinct RES.* ");
		databaseOuterJoinLimitBetweenStatements.put("mssql", ", row_number() over (ORDER BY ${mssqlOrDB2OrderBy}) rnk FROM ( select distinct ");
		databaseSpecificOrderByStatements.put("mssql", "");
		databaseSpecificLimitBeforeNativeQueryStatements.put("mssql", "SELECT SUB.* FROM ( select RES.* , row_number() over (ORDER BY ${orderBy}) rnk FROM (");
		addDatabaseSpecificStatement("mssql", "selectExclusiveJobsToExecute", "selectExclusiveJobsToExecute_integerBoolean");
		addDatabaseSpecificStatement("mssql", "selectExecutionByNativeQuery", "selectExecutionByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectHistoricActivityInstanceByNativeQuery", "selectHistoricActivityInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectHistoricProcessInstanceByNativeQuery", "selectHistoricProcessInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectHistoricTaskInstanceByNativeQuery", "selectHistoricTaskInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectTaskByNativeQuery", "selectTaskByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectProcessDefinitionByNativeQuery", "selectProcessDefinitionByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectDeploymentByNativeQuery", "selectDeploymentByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectGroupByNativeQuery", "selectGroupByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectUserByNativeQuery", "selectUserByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectModelByNativeQuery", "selectModelByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql", "selectHistoricDetailByNativeQuery", "selectHistoricDetailByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql",  "selectHistoricVariableInstanceByNativeQuery", "selectHistoricVariableInstanceByNativeQuery_mssql_or_db2");
		addDatabaseSpecificStatement("mssql",
				"selectTaskWithVariablesByQueryCriteria",
				"selectTaskWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("mssql",
				"selectProcessInstanceWithVariablesByQueryCriteria",
				"selectProcessInstanceWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("mssql",
				"selectHistoricProcessInstancesWithVariablesByQueryCriteria",
				"selectHistoricProcessInstancesWithVariablesByQueryCriteria_mssql_or_db2");
		addDatabaseSpecificStatement("mssql",
				"selectHistoricTaskInstancesWithVariablesByQueryCriteria",
				"selectHistoricTaskInstancesWithVariablesByQueryCriteria_mssql_or_db2");
	}

	protected static void addDatabaseSpecificStatement(String databaseType,
			String activitiStatement, String ibatisStatement) {
		Map<String, String> specificStatements = databaseSpecificStatements
				.get(databaseType);
		if (specificStatements == null) {
			specificStatements = new HashMap<String, String>();
			databaseSpecificStatements.put(databaseType, specificStatements);
		}
		specificStatements.put(activitiStatement, ibatisStatement);
	}
}
