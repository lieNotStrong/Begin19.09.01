package com.neuedu.common;

public class EnumClass {

    public enum Role{

        ROLE_USER(1,"普通用户"),
        ROLE_ADMIN(0,"管理员用户")
        ;
        private int roleCode;
        private String roleDesc;

        Role(int roleCode, String roleDesc) {
            this.roleCode = roleCode;
            this.roleDesc = roleDesc;
        }

        public int getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(int roleCode) {
            this.roleCode = roleCode;
        }

        public String getRoleDesc() {
            return roleDesc;
        }

        public void setRoleDesc(String roleDesc) {
            this.roleDesc = roleDesc;
        }
    }

    public enum ProductEnum{


        PRODUCT_ONLINE(1,"商品在售"),
        PRODUCT_OUT(2,"商品下架"),
        PRODUCT_DELETE(3,"商品删除")
        ;
        private Integer productTypeCode;
        private String productTypeDesc;

        ProductEnum(Integer productTypeCode, String productTypeDesc) {
            this.productTypeCode = productTypeCode;
            this.productTypeDesc = productTypeDesc;
        }

        ProductEnum() {
        }

        public Integer getProductTypeCode() {
            return productTypeCode;
        }

        public void setProductTypeCode(Integer productTypeCode) {
            this.productTypeCode = productTypeCode;
        }

        public String getProductTypeDesc() {
            return productTypeDesc;
        }

        public void setProductTypeDesc(String productTypeDesc) {
            this.productTypeDesc = productTypeDesc;
        }
    }


    public enum CartIsChecked{

        PRODUCT_CHECKED(1,"已勾选"),
        PRODUCT_UNCHECKED(0,"未勾选")
        ;
        private int roleCode;
        private String roleDesc;

        CartIsChecked(int roleCode, String roleDesc) {
            this.roleCode = roleCode;
            this.roleDesc = roleDesc;
        }

        public int getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(int roleCode) {
            this.roleCode = roleCode;
        }

        public String getRoleDesc() {
            return roleDesc;
        }

        public void setRoleDesc(String roleDesc) {
            this.roleDesc = roleDesc;
        }
    }



}
