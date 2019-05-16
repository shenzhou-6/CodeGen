# codegen
kotlin data class 生成器

在pom或build文件中引入groovy

文件中config为数据库配置

dir　为文件输出目录,生成文件名称为Entity.kt

packageName　＝　项目包名

typeMap中key＝数据库类型,value=kotlin or java 类型

如果缺少数据类型，直接在typeMap中添加或修改即可

