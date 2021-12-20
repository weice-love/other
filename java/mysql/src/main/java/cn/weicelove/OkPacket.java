//package cn.weicelove;
//
//public class OkPacket extends MySQLPacket {
//    public static final byte FIELD_COUNT = 0x00;
//    public static final byte[] OK = new byte[] { 7, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0 };
//    public static final byte[] AUTH_OK = new byte[] { 7, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0 };
//    public byte fieldCount = FIELD_COUNT;
//    public long affectedRows;
//    public long insertId;
//    public int serverStatus;
//    public int warningCount;
//    public byte[] message;
//}