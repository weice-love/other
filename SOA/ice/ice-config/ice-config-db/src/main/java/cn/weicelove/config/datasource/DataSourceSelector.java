package cn.weicelove.config.datasource;

public class DataSourceSelector {

    private static final ThreadLocal<String> DATASOURCE_CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDbType(String dbType) {
        DATASOURCE_CONTEXT_HOLDER.set(dbType);
    }

    public static String getDbType() {
        return DATASOURCE_CONTEXT_HOLDER.get();
    }

    public static void clearDbType() {
        DATASOURCE_CONTEXT_HOLDER.remove();
    }

}
