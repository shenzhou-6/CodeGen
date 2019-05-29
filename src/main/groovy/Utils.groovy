/**
 * 驼峰转换
 * @param name 名称
 * @param firstUp  首字母是否需要大写
 * @return
 */
static def camel(name, firstUp) {
    if (firstUp) name = name.replace(name[0], name[0].toUpperCase())
    if (name.contains("_")) {
        def index = name.indexOf("_")
        while (index != -1) {
            name = name.replace(name[index..index + 1], name[index + 1].toUpperCase())
            index = name.indexOf("_")
        }
    }
    return name
}