import groovy.sql.Sql

import static Config.*



def metaData = Sql.newInstance(databaseConfig).connection.metaData

def tables = metaData.getTables(null, null, "", null)


new File(dir + 'Entity.kt').withPrintWriter { out ->
    out.println "package $packageName"
    out.println "import java.time.LocalDateTime"
    out.println "import java.time.LocalTime"
    out.println "import java.time.LocalDate"
    out.println "import javax.persistence.Entity"
    out.println "import javax.persistence.Id"
    out.println "import javax.persistence.Table"
    out.println ""
    while (tables.next()) {
        def tableName = tables.getString("TABLE_NAME")
        def entityName = Utils.camel(tableName, true)
        out.println "@Entity"
        out.println "@Table(name=\"$tableName\")"
        out.println "class $entityName {"
        def primaryKey = metaData.getPrimaryKeys(null, null, tableName)
        def primaryKeyName = null
        while (primaryKey.next()) primaryKeyName = primaryKey.getString("COLUMN_NAME")
        def columns = metaData.getColumns(null, null, tableName, null)
        def nextColumn = columns.next()
        while (nextColumn) {
            def columnName = columns.getString("COLUMN_NAME")
            def remark = columns.getString("REMARKS")
            def dataType = columns.getString("TYPE_NAME")
            //def nullable = columns.getString("NULLABLE")
            def entityColumnName = Utils.camel(columnName, false)
            def colunmType = kotlinTypeMap[dataType]
            def colunm = "var $entityColumnName:$colunmType"
            //if (nullable == "1") colunm = colunm + "?"
            colunm = colunm + "? = null"
            nextColumn = columns.next()
            if (nextColumn){
                colunm = colunm
            }
            if (remark.size()>0) colunm = colunm + " //$remark"
            if (primaryKeyName == entityColumnName){
                out.println "@Id"
            }
            out.println colunm
        }
        out.println "}"
        out.println ""
    }
}
