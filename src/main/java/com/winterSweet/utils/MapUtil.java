/*
 * Copyright [2017] [Butterfly Killer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.winterSweet.utils;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/14 21:36
 * <p>
 * Description: 地图计算工具
 */
public class MapUtil {
    private static final double PI = Math.PI; // 圆周率
    private static final double EARTH_RADIUS = 6371004; // 地球半径
    private static final double RAD = Math.PI / 180.0;

    public MapUtil() {
    }

    /**
     * 给出坐标和半径，返回最大和最小经纬度坐标
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param radius    半径
     * @return 最大、最小经纬度坐标值数组
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    public static double[] getAround(double latitude, double longitude, int radius) {
        Double degree = (24901 * 1609) / 360.0;
        double radiusMile = radius;
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * radiusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * radiusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[]{minLat, minLng, maxLat, maxLng};
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param longitudeA 经度A
     * @param latitudeA  纬度A
     * @param longitudeB 经度B
     * @param latitudeB  纬度B
     * @return s 距离值
     */
    public static double getDistance(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        double radLat1 = latitudeA * RAD;
        double radLat2 = latitudeB * RAD;
        double a = radLat1 - radLat2;
        double b = (longitudeA - longitudeB) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    // 测试
    public static void main(String[] args) {
        double lat1 = 34.264648;
        double lon1 = 108.952736;

        int radius = 1000;
        getAround(lat1,lon1,radius);

        double dis = getDistance(108.952736,34.264648,116.407288,39.904549);
        System.out.println(dis);
    }
}
