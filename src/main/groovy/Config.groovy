class Config {

    /**
     * 数据库配置
     */
    static def databaseConfig = [
            'url'            : 'jdbc:mysql://127.0.0.1/demo',
            'user'           : 'root',
            'password'       : '123456',
            'driverClassName': 'com.mysql.jdbc.Driver'
    ]

    /**
     * mysql type -> kotlin type
     */
    static def kotlinTypeMap = ["CHAR"    : "String", "VARCHAR": "String", "TEXT": "String", "MEDIUMTEXT": "String",
                                "TINYINT" : "Int", "SMALLINT": "Int", "MEDIUMINT": "Int", "INT": "Int", "INT UNSIGNED": "Int",
                                "BIGINT"  : "Long",
                                "FLOAT"   : "Float", "DOUBLE": "Double", "DATE": "LocalDate",
                                "BIT"     : "Boolean",
                                "DATETIME": "LocalDateTime", "TIMESTAMP": "LocalDateTime",
                                "TIME"    : "LocalTime"
    ]

    /**
     * mysql type -> java type
     */
    static def javaTypeMap = ["CHAR"    : "String", "VARCHAR": "String", "TEXT": "String", "MEDIUMTEXT": "String",
                                "TINYINT" : "Integer", "SMALLINT": "Integer", "MEDIUMINT": "Integer", "INT": "Integer", "Integer UNSIGNED": "Int",
                                "BIGINT"  : "Long",
                                "FLOAT"   : "Float", "DOUBLE": "Double", "DATE": "LocalDate",
                                "BIT"     : "Boolean",
                                "DATETIME": "LocalDateTime", "TIMESTAMP": "LocalDateTime",
                                "TIME"    : "LocalTime"
    ]
    /**
     * 包名
     */
    static def packageName = "cc.shallow.entity"


    /**
     * 生成文件目录
     */
    static def dir = "/home/weixing/entity/"

}
