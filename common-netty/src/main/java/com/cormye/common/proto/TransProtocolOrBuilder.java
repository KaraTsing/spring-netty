// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.cormye.common.proto;

public interface TransProtocolOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TransProtocol)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *数据包类型
   * </pre>
   *
   * <code>.PackType packType = 1;</code>
   */
  int getPackTypeValue();
  /**
   * <pre>
   *数据包类型
   * </pre>
   *
   * <code>.PackType packType = 1;</code>
   */
  com.cormye.common.proto.PackType getPackType();

  /**
   * <code>.ClientOnline clientOnline = 2;</code>
   */
  boolean hasClientOnline();
  /**
   * <code>.ClientOnline clientOnline = 2;</code>
   */
  com.cormye.common.proto.ClientOnline getClientOnline();
  /**
   * <code>.ClientOnline clientOnline = 2;</code>
   */
  com.cormye.common.proto.ClientOnlineOrBuilder getClientOnlineOrBuilder();

  public com.cormye.common.proto.TransProtocol.PackBodyCase getPackBodyCase();
}
