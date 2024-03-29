package cn.weicelove.constants;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/23 10:52
 */
public class CapabilityConstants {

    public static final int CLIENT_LONG_PASSWORD = 0x00000001;
    public static final int CLIENT_FOUND_ROWS = 0x00000002;
    public static final int CLIENT_LONG_FLAG = 0x00000004;
    /**
     * Database (schema) name can be specified on connect in Handshake Response
     */
    public static final int CLIENT_CONNECT_WITH_DB = 0x00000008;
    public static final int CLIENT_NO_SCHEMA = 0x00000010;
    public static final int CLIENT_COMPRESS = 0x00000020;
    public static final int CLIENT_ODBC = 0x00000040;
    public static final int CLIENT_LOCAL_FILES = 0x00000080;
    public static final int CLIENT_IGNORE_SPACE = 0x00000100;
    public static final int CLIENT_PROTOCOL_41 = 0x00000200;
    public static final int CLIENT_INTERACTIVE = 0x00000400;
    public static final int CLIENT_SSL = 0x00000800;
    public static final int CLIENT_IGNORE_SIGPIPE = 0x00001000;
    public static final int CLIENT_TRANSACTIONS = 0x00002000;
    public static final int CLIENT_RESERVED = 0x00004000;
    /**
     * Server
     *      Supports Authentication::Native41.
     * Client
     *      Supports Authentication::Native41.
     */
   public static final int CLIENT_SECURE_CONNECTION = 0x00008000;
   public static final int CLIENT_MULTI_STATEMENTS = 0x00010000;
   public static final int CLIENT_MULTI_RESULTS = 0x00020000;
   public static final int CLIENT_PS_MULTI_RESULTS = 0x00040000;
   public static final int CLIENT_PLUGIN_AUTH = 0x00080000;
   public static final int CLIENT_CONNECT_ATTRS = 0x00100000;
    /**
     * Server
     *      Understands length-encoded integer for auth response data in Protocol::HandshakeResponse41.
     * Client
     *      Length of auth response data in Protocol::HandshakeResponse41 is a length-encoded integer.
     */
    public static final int CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA = 0x00200000;
    public static final int CLIENT_CAN_HANDLE_EXPIRED_PASSWORDS = 0x00400000;
    public static final int CLIENT_SESSION_TRACK = 0x00800000;
    public static final int CLIENT_DEPRECATE_EOF = 0x01000000;
}
