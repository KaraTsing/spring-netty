// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.cormye.common.proto;

/**
 * Protobuf enum {@code PackType}
 */
public enum PackType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>PINGER = 0;</code>
   */
  PINGER(0),
  /**
   * <pre>
   *客户端上线 发送自己的ip和设备编号
   * </pre>
   *
   * <code>CLIENT_ONLINE = 1;</code>
   */
  CLIENT_ONLINE(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>PINGER = 0;</code>
   */
  public static final int PINGER_VALUE = 0;
  /**
   * <pre>
   *客户端上线 发送自己的ip和设备编号
   * </pre>
   *
   * <code>CLIENT_ONLINE = 1;</code>
   */
  public static final int CLIENT_ONLINE_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static PackType valueOf(int value) {
    return forNumber(value);
  }

  public static PackType forNumber(int value) {
    switch (value) {
      case 0: return PINGER;
      case 1: return CLIENT_ONLINE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PackType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      PackType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PackType>() {
          public PackType findValueByNumber(int number) {
            return PackType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.cormye.common.proto.TranData.getDescriptor().getEnumTypes().get(0);
  }

  private static final PackType[] VALUES = values();

  public static PackType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private PackType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:PackType)
}

