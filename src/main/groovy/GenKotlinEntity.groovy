import groovy.sql.Sql

config = [
        'url'            : 'jdbc:mysql://localhost:3306/diabetes',
        'user'           : 'root',
        'password'       : '123456',
        'driverClassName': 'com.mysql.jdbc.Driver'
]
def dir = "/home/weixing/Desktop/"
def packageName = "cc.shallow.entity"

def metaData = Sql.newInstance(config).connection.metaData

def tables = metaData.getTables(null, null, "", null)


def typeMap = ["CHAR"    : "String", "VARCHAR": "String", "TEXT": "String","MEDIUMTEXT":"String",
               "TINYINT" : "Int", "SMALLINT": "Int", "MEDIUMINT": "Int", "INT": "Int","INT UNSIGNED":"Int",
               "BIGINT"  : "Long",
               "FLOAT"   : "Float", "DOUBLE": "Double", "DATE": "LocalDate",
               "BIT"     : "Boolean",
               "DATETIME": "LocalDateTime", "TIMESTAMP": "LocalDateTime",
               "TIME"    : "LocalTime"
]

new File(dir + 'Entity.kt').withPrintWriter { out ->
    out.println "package $packageName"
    out.println "import java.time.LocalDateTime"
    out.println "import java.time.LocalTime"
    out.println "import java.time.LocalDate"
    out.println ""
    while (tables.next()) {
        def tableName = tables.getString("TABLE_NAME")
        def entityName = camel(tableName, true)
        out.println "data class $entityName ("
        def primaryKey = metaData.getPrimaryKeys(null, null, tableName)
        def primaryKeyName = null
        while (primaryKey.next()) primaryKeyName = primaryKey.getString("COLUMN_NAME")
        def columns = metaData.getColumns(null, null, tableName, null)
        def nextColumn = columns.next()
        while (nextColumn) {
            def columnName = columns.getString("COLUMN_NAME")
            def remark = columns.getString("REMARKS")
            def dataType = columns.getString("TYPE_NAME")
            def nullable = columns.getString("NULLABLE")
            def entityColumnName = camel(columnName, false)
            def colunmType = typeMap[dataType]
            def colunm = "var $entityColumnName:$colunmType"
            if (nullable == "1") colunm = colunm + "?"
            nextColumn = columns.next()
            if (nextColumn){
                colunm = colunm + ","
            }
            if (remark.size()>0) colunm = colunm + " //$remark"
            out.println colunm
        }
        out.println ")"
        out.println ""
    }
}

def camel(name, firstUp) {
    if (firstUp) name = name.replace(name[0], name[0].toUpperCase())
    if (name.contains("_")) {
        index = name.indexOf("_")
        while (index != -1) {
            name = name.replace(name[index..index + 1], name[index + 1].toUpperCase())
            index = name.indexOf("_")
        }
    }
    return name
}