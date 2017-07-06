package com.vv.zvv.JavaBean;

import java.util.List;

public class AddressDetailsEntity {
    public int SellerId;
    public int OrderId;
    public String RecipientName;
    public String Mobile;
    public String ZipCode;
    public String Province;
    public String City;
    public String Area;
    public String Address;
    public Data ProvinceItems;

    public int getSellerId() {
        return SellerId;
    }

    public void setSellerId(int sellerId) {
        SellerId = sellerId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Data getProvinceItems() {
        return ProvinceItems;
    }

    public void setProvinceItems(Data provinceItems) {
        ProvinceItems = provinceItems;
    }

    public class Data {
        public List<ProvinceEntity> Province;

        public List<ProvinceEntity> getProvince() {
            return Province;
        }

        public void setProvince(List<ProvinceEntity> province) {
            Province = province;
        }
    }

    public class ProvinceEntity {
        public String Name;
        public List<CityEntity> City;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public List<CityEntity> getCity() {
            return City;
        }

        public void setCity(List<CityEntity> city) {
            City = city;
        }

        public class CityEntity {
            public String Name;
            public List<AreaEntity> Area;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public List<AreaEntity> getArea() {
                return Area;
            }

            public void setArea(List<AreaEntity> area) {
                Area = area;
            }
        }
        public class AreaEntity {
            public String Name;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }
        }


    }


}
