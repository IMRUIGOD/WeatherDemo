package com.IMRUIGOD.weatherdemo;

import java.util.List;

/**
 * 天气实体类
 */
public class Weather {

    /**
     * cityInfo : {"city":"长春市","citykey":"101060101","parent":"吉林","updateTime":"11:16"}
     * data : {"forecast":[{"aqi":31,"date":"11","fl":"3级","fx":"西南风","high":"高温 24℃","low":"低温 17℃","notice":"雨虽小，注意保暖别感冒","sunrise":"03:56","sunset":"19:20","type":"小雨","week":"星期四","ymd":"2020-06-11"},{"aqi":28,"date":"12","fl":"2级","fx":"西南风","high":"高温 25℃","low":"低温 14℃","notice":"出门最好穿雨衣，勿挡视线","sunrise":"03:55","sunset":"19:21","type":"大雨","week":"星期五","ymd":"2020-06-12"},{"aqi":26,"date":"13","fl":"3级","fx":"西南风","high":"高温 27℃","low":"低温 19℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:55","sunset":"19:21","type":"多云","week":"星期六","ymd":"2020-06-13"},{"aqi":32,"date":"14","fl":"4级","fx":"西南风","high":"高温 31℃","low":"低温 18℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:56","sunset":"19:22","type":"阴","week":"星期日","ymd":"2020-06-14"},{"aqi":35,"date":"15","fl":"3级","fx":"西南风","high":"高温 29℃","low":"低温 18℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:56","sunset":"19:23","type":"阴","week":"星期一","ymd":"2020-06-15"},{"aqi":20,"date":"16","fl":"2级","fx":"东南风","high":"高温 25℃","low":"低温 13℃","notice":"雨虽小，注意保暖别感冒","sunrise":"03:56","sunset":"19:23","type":"小雨","week":"星期二","ymd":"2020-06-16"},{"aqi":27,"date":"17","fl":"2级","fx":"东北风","high":"高温 28℃","low":"低温 16℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:56","sunset":"19:24","type":"晴","week":"星期三","ymd":"2020-06-17"},{"aqi":21,"date":"18","fl":"3级","fx":"南风","high":"高温 30℃","low":"低温 18℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:56","sunset":"19:24","type":"晴","week":"星期四","ymd":"2020-06-18"},{"aqi":12,"date":"19","fl":"2级","fx":"西南风","high":"高温 25℃","low":"低温 15℃","notice":"出门最好穿雨衣，勿挡视线","sunrise":"03:56","sunset":"19:24","type":"大雨","week":"星期五","ymd":"2020-06-19"},{"aqi":16,"date":"20","fl":"2级","fx":"东南风","high":"高温 30℃","low":"低温 17℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:57","sunset":"19:24","type":"晴","week":"星期六","ymd":"2020-06-20"},{"aqi":20,"date":"21","fl":"3级","fx":"西南风","high":"高温 31℃","low":"低温 23℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:57","sunset":"19:25","type":"多云","week":"星期日","ymd":"2020-06-21"},{"aqi":25,"date":"22","fl":"2级","fx":"东南风","high":"高温 26℃","low":"低温 15℃","notice":"记得随身携带雨伞哦","sunrise":"03:57","sunset":"19:25","type":"中雨","week":"星期一","ymd":"2020-06-22"},{"aqi":46,"date":"23","fl":"2级","fx":"西南风","high":"高温 32℃","low":"低温 21℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:57","sunset":"19:25","type":"晴","week":"星期二","ymd":"2020-06-23"},{"aqi":47,"date":"24","fl":"4级","fx":"西南风","high":"高温 36℃","low":"低温 23℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:58","sunset":"19:25","type":"多云","week":"星期三","ymd":"2020-06-24"},{"aqi":37,"date":"25","fl":"2级","fx":"南风","high":"高温 32℃","low":"低温 19℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:58","sunset":"19:25","type":"阴","week":"星期四","ymd":"2020-06-25"}],"ganmao":"各类人群可自由活动","pm10":24,"pm25":3,"quality":"优","shidu":"44%","wendu":"22","yesterday":{"aqi":16,"date":"10","fl":"3级","fx":"西南风","high":"高温 25℃","low":"低温 18℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:56","sunset":"19:20","type":"多云","week":"星期三","ymd":"2020-06-10"}}
     * date : 20200611
     * message : success感谢又拍云(upyun.com)提供CDN赞助
     * status : 200
     * time : 2020-06-11 12:30:30
     */

    private CityInfoBean cityInfo;
    private DataBean data;
    private String date;
    private String message;
    private int status;
    private String time;

    public CityInfoBean getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoBean cityInfo) {
        this.cityInfo = cityInfo;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class CityInfoBean {
        /**
         * city : 长春市
         * citykey : 101060101
         * parent : 吉林
         * updateTime : 11:16
         */

        private String city;
        private String citykey;
        private String parent;
        private String updateTime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCitykey() {
            return citykey;
        }

        public void setCitykey(String citykey) {
            this.citykey = citykey;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class DataBean {
        /**
         * forecast : [{"aqi":31,"date":"11","fl":"3级","fx":"西南风","high":"高温 24℃","low":"低温 17℃","notice":"雨虽小，注意保暖别感冒","sunrise":"03:56","sunset":"19:20","type":"小雨","week":"星期四","ymd":"2020-06-11"},{"aqi":28,"date":"12","fl":"2级","fx":"西南风","high":"高温 25℃","low":"低温 14℃","notice":"出门最好穿雨衣，勿挡视线","sunrise":"03:55","sunset":"19:21","type":"大雨","week":"星期五","ymd":"2020-06-12"},{"aqi":26,"date":"13","fl":"3级","fx":"西南风","high":"高温 27℃","low":"低温 19℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:55","sunset":"19:21","type":"多云","week":"星期六","ymd":"2020-06-13"},{"aqi":32,"date":"14","fl":"4级","fx":"西南风","high":"高温 31℃","low":"低温 18℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:56","sunset":"19:22","type":"阴","week":"星期日","ymd":"2020-06-14"},{"aqi":35,"date":"15","fl":"3级","fx":"西南风","high":"高温 29℃","low":"低温 18℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:56","sunset":"19:23","type":"阴","week":"星期一","ymd":"2020-06-15"},{"aqi":20,"date":"16","fl":"2级","fx":"东南风","high":"高温 25℃","low":"低温 13℃","notice":"雨虽小，注意保暖别感冒","sunrise":"03:56","sunset":"19:23","type":"小雨","week":"星期二","ymd":"2020-06-16"},{"aqi":27,"date":"17","fl":"2级","fx":"东北风","high":"高温 28℃","low":"低温 16℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:56","sunset":"19:24","type":"晴","week":"星期三","ymd":"2020-06-17"},{"aqi":21,"date":"18","fl":"3级","fx":"南风","high":"高温 30℃","low":"低温 18℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:56","sunset":"19:24","type":"晴","week":"星期四","ymd":"2020-06-18"},{"aqi":12,"date":"19","fl":"2级","fx":"西南风","high":"高温 25℃","low":"低温 15℃","notice":"出门最好穿雨衣，勿挡视线","sunrise":"03:56","sunset":"19:24","type":"大雨","week":"星期五","ymd":"2020-06-19"},{"aqi":16,"date":"20","fl":"2级","fx":"东南风","high":"高温 30℃","low":"低温 17℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:57","sunset":"19:24","type":"晴","week":"星期六","ymd":"2020-06-20"},{"aqi":20,"date":"21","fl":"3级","fx":"西南风","high":"高温 31℃","low":"低温 23℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:57","sunset":"19:25","type":"多云","week":"星期日","ymd":"2020-06-21"},{"aqi":25,"date":"22","fl":"2级","fx":"东南风","high":"高温 26℃","low":"低温 15℃","notice":"记得随身携带雨伞哦","sunrise":"03:57","sunset":"19:25","type":"中雨","week":"星期一","ymd":"2020-06-22"},{"aqi":46,"date":"23","fl":"2级","fx":"西南风","high":"高温 32℃","low":"低温 21℃","notice":"愿你拥有比阳光明媚的心情","sunrise":"03:57","sunset":"19:25","type":"晴","week":"星期二","ymd":"2020-06-23"},{"aqi":47,"date":"24","fl":"4级","fx":"西南风","high":"高温 36℃","low":"低温 23℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:58","sunset":"19:25","type":"多云","week":"星期三","ymd":"2020-06-24"},{"aqi":37,"date":"25","fl":"2级","fx":"南风","high":"高温 32℃","low":"低温 19℃","notice":"不要被阴云遮挡住好心情","sunrise":"03:58","sunset":"19:25","type":"阴","week":"星期四","ymd":"2020-06-25"}]
         * ganmao : 各类人群可自由活动
         * pm10 : 24.0
         * pm25 : 3.0
         * quality : 优
         * shidu : 44%
         * wendu : 22
         * yesterday : {"aqi":16,"date":"10","fl":"3级","fx":"西南风","high":"高温 25℃","low":"低温 18℃","notice":"阴晴之间，谨防紫外线侵扰","sunrise":"03:56","sunset":"19:20","type":"多云","week":"星期三","ymd":"2020-06-10"}
         */

        private String ganmao;
        private double pm10;
        private double pm25;
        private String quality;
        private String shidu;
        private String wendu;
        private DayBean yesterday;
        private List<DayBean> forecast;

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public DayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(DayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<DayBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<DayBean> forecast) {
            this.forecast = forecast;
        }

        public static class DayBean {
            /**
             * aqi : 31
             * date : 11
             * fl : 3级
             * fx : 西南风
             * high : 高温 24℃
             * low : 低温 17℃
             * notice : 雨虽小，注意保暖别感冒
             * sunrise : 03:56
             * sunset : 19:20
             * type : 小雨
             * week : 星期四
             * ymd : 2020-06-11
             */

            private int aqi;
            private String date;
            private String fl;
            private String fx;
            private String high;
            private String low;
            private String notice;
            private String sunrise;
            private String sunset;
            private String type;
            private String week;
            private String ymd;

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getYmd() {
                return ymd;
            }

            public void setYmd(String ymd) {
                this.ymd = ymd;
            }
        }
    }
}