package com.lena.asp.common.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author lilingfei
 * @date 2018/9/10
 */
public class ItemEntity extends BaseEntity {

    /**
     * Token : null
     * Result : {"productid":205,"ProductName":"小苹果","mdetails":"很不错的苹果","pCode":"3310010001","sales":4,"cost":4,"inventory":85,"weight":0,"Gweight":0,"shopid":27,"unit":"只","L":0,"W":0,"H":0,"ServiceType":1,"contactPhone":"15868666921","SKUprice_Max":4,"SKUprice_Min":4,"SKUprice_Avg":4,"ShopName":"买就发小超市(测试专用，请勿下单)","RY_id":"a15868666921","TB_SKU":[],"mainImg":[{"imgserver":"http://imgone.jjsqzg.com/images/eShop/ShopProductPics/27/20180403151800011859.jpeg","imgFirstFrame":null,"fileType":0}],"CM_Discount":[],"TB_FullCuts":[],"TB_Discount":[],"TB_Coupons":[],"TB_Evaluates":null,"EvaluateCount":0}
     */


    private ResultBean Result;

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        /**
         * productid : 205
         * ProductName : 小苹果
         * mdetails : 很不错的苹果
         * pCode : 3310010001
         * sales : 4.0
         * cost : 4.0
         * inventory : 85
         * weight : 0.0
         * Gweight : 0.0
         * shopid : 27
         * unit : 只
         * L : 0
         * W : 0
         * H : 0
         * ServiceType : 1
         * contactPhone : 15868666921
         * SKUprice_Max : 4.0
         * SKUprice_Min : 4.0
         * SKUprice_Avg : 4.0
         * ShopName : 买就发小超市(测试专用，请勿下单)
         * RY_id : a15868666921
         * TB_SKU : []
         * mainImg : [{"imgserver":"http://imgone.jjsqzg.com/images/eShop/ShopProductPics/27/20180403151800011859.jpeg","imgFirstFrame":null,"fileType":0}]
         * CM_Discount : []
         * TB_FullCuts : []
         * TB_Discount : []
         * TB_Coupons : []
         * TB_Evaluates : null
         * EvaluateCount : 0
         */

        private int productid;
        private String ProductName;
        private String mdetails;
        private String pCode;
        private double sales;
        private double cost;
        private int inventory;
        private double weight;
        private double Gweight;
        private int shopid;
        private String unit;
        private int L;
        private int W;
        private int H;
        private int ServiceType;
        private String contactPhone;
        private double SKUprice_Max;
        private double SKUprice_Min;
        private double SKUprice_Avg;
        private String ShopName;
        private String RY_id;
        private Object TB_Evaluates;
        private int EvaluateCount;
        private List<?> TB_SKU;
        private List<MainImgBean> mainImg;
        private List<?> CM_Discount;
        private List<?> TB_FullCuts;
        private List<?> TB_Discount;
        private List<?> TB_Coupons;

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getMdetails() {
            return mdetails;
        }

        public void setMdetails(String mdetails) {
            this.mdetails = mdetails;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public double getSales() {
            return sales;
        }

        public void setSales(double sales) {
            this.sales = sales;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getGweight() {
            return Gweight;
        }

        public void setGweight(double Gweight) {
            this.Gweight = Gweight;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getL() {
            return L;
        }

        public void setL(int L) {
            this.L = L;
        }

        public int getW() {
            return W;
        }

        public void setW(int W) {
            this.W = W;
        }

        public int getH() {
            return H;
        }

        public void setH(int H) {
            this.H = H;
        }

        public int getServiceType() {
            return ServiceType;
        }

        public void setServiceType(int ServiceType) {
            this.ServiceType = ServiceType;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public double getSKUprice_Max() {
            return SKUprice_Max;
        }

        public void setSKUprice_Max(double SKUprice_Max) {
            this.SKUprice_Max = SKUprice_Max;
        }

        public double getSKUprice_Min() {
            return SKUprice_Min;
        }

        public void setSKUprice_Min(double SKUprice_Min) {
            this.SKUprice_Min = SKUprice_Min;
        }

        public double getSKUprice_Avg() {
            return SKUprice_Avg;
        }

        public void setSKUprice_Avg(double SKUprice_Avg) {
            this.SKUprice_Avg = SKUprice_Avg;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getRY_id() {
            return RY_id;
        }

        public void setRY_id(String RY_id) {
            this.RY_id = RY_id;
        }

        public Object getTB_Evaluates() {
            return TB_Evaluates;
        }

        public void setTB_Evaluates(Object TB_Evaluates) {
            this.TB_Evaluates = TB_Evaluates;
        }

        public int getEvaluateCount() {
            return EvaluateCount;
        }

        public void setEvaluateCount(int EvaluateCount) {
            this.EvaluateCount = EvaluateCount;
        }

        public List<?> getTB_SKU() {
            return TB_SKU;
        }

        public void setTB_SKU(List<?> TB_SKU) {
            this.TB_SKU = TB_SKU;
        }

        public List<MainImgBean> getMainImg() {
            return mainImg;
        }

        public void setMainImg(List<MainImgBean> mainImg) {
            this.mainImg = mainImg;
        }

        public List<?> getCM_Discount() {
            return CM_Discount;
        }

        public void setCM_Discount(List<?> CM_Discount) {
            this.CM_Discount = CM_Discount;
        }

        public List<?> getTB_FullCuts() {
            return TB_FullCuts;
        }

        public void setTB_FullCuts(List<?> TB_FullCuts) {
            this.TB_FullCuts = TB_FullCuts;
        }

        public List<?> getTB_Discount() {
            return TB_Discount;
        }

        public void setTB_Discount(List<?> TB_Discount) {
            this.TB_Discount = TB_Discount;
        }

        public List<?> getTB_Coupons() {
            return TB_Coupons;
        }

        public void setTB_Coupons(List<?> TB_Coupons) {
            this.TB_Coupons = TB_Coupons;
        }

        public static class MainImgBean {
            /**
             * imgserver : http://imgone.jjsqzg.com/images/eShop/ShopProductPics/27/20180403151800011859.jpeg
             * imgFirstFrame : null
             * fileType : 0
             */

            private String imgserver;
            private Object imgFirstFrame;
            private int fileType;

            public String getImgserver() {
                return imgserver;
            }

            public void setImgserver(String imgserver) {
                this.imgserver = imgserver;
            }

            public Object getImgFirstFrame() {
                return imgFirstFrame;
            }

            public void setImgFirstFrame(Object imgFirstFrame) {
                this.imgFirstFrame = imgFirstFrame;
            }

            public int getFileType() {
                return fileType;
            }

            public void setFileType(int fileType) {
                this.fileType = fileType;
            }
        }
    }
}
