# codegen


Config -> 数据库，生成目录等配置

dir　为文件输出目录,生成文件名称为Entity.kt

packageName　＝　项目包名

typeMap中key＝数据库类型,value=kotlin or java 类型

如果缺少数据类型，直接在typeMap中添加或修改即可

生成java实体文件请运行 GenJavaEntity

生成kotlin实体文件请运行 GenJavaEntity



java实体类没有生成set get 方法,而是引入了lombok

每个java类为一个文件,kotlin所有类在同一个文件
