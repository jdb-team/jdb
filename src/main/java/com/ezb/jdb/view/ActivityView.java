package com.ezb.jdb.view;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.User;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

/**
 * 活动视图包装
 * author : liufeng
 * create time:2015/8/10 11:41
 */
public class ActivityView {

    public static String convert2Json(List<Activity> list, User user) {
        JSONArray jsonArray = new JSONArray();
        for (Activity activity : list) {
            JSONObject jsonObject = getJsonObject(user, activity);
            jsonArray.add(jsonObject);
        }
        return ResponseData.getResData(jsonArray);
    }

    public static JSONObject getJsonObject(User user, Activity activity) {

        JSONObject jsonObject = new JSONObject();
        DateTime dateTime = new DateTime(activity.getStartTime());

        jsonObject.put("id", activity.getId());
        jsonObject.put("year", dateTime.toString("yyyy"));
        jsonObject.put("month", dateTime.toString("MM月"));
        jsonObject.put("day", dateTime.toString("dd日"));
        jsonObject.put("week", dateTime.dayOfWeek().getAsShortText());
        jsonObject.put("title", activity.getTitle());
        jsonObject.put("address", activity.getAddress());
        jsonObject.put("startTime", dateTime.toString("yyyy-MM-dd HH:mm"));
        jsonObject.put("endTime", new DateTime(activity.getEndTime()).toString("yyyy-MM-dd HH:mm"));

        if(null == activity.getStartTime()){
            jsonObject.put("year", "");
            jsonObject.put("month", "");
            jsonObject.put("day", "");
            jsonObject.put("startTime", "");
        }

        if(null == activity.getEndTime()){
            jsonObject.put("endTime", "");
        }

        if (null == activity.getJoinUsers()) {
            jsonObject.put("joinCount", 0);
        } else {
            jsonObject.put("joinCount", activity.getJoinUsers().size());
        }
        jsonObject.put("state", getState(activity));

        if (null == activity.getJoinUsers() || !activity.getJoinUsers().contains(user)) {
            jsonObject.put("join", "0");
        } else {
            jsonObject.put("join", "1");
        }
        return jsonObject;
    }

    /**
     * 1 报名中 2 已满员 3 已截止 4 进行中 5 已结束
     *
     * @param activity
     * @return
     */
    public static String getState(Activity activity) {
        DateTime nowTime = DateTime.now();
        if (nowTime.isAfter(new DateTime(activity.getCloseTime()))) {
            return "3";
        }
        if (nowTime.isAfter(new DateTime(activity.getStartTime()))
                && nowTime.isBefore(new DateTime(activity.getEndTime()))) {
            return "4";
        }
        if (nowTime.isAfter(new DateTime(activity.getEndTime()))) {
            return "5";
        }
        if (activity.getJoinUsers() == null ||
                activity.getJoinUsers().size() < activity.getPersonLimit()) {
            return "1";
        }
        if (activity.getJoinUsers().size() >= activity.getPersonLimit()) {
            return "2";
        }
        return "";
    }

    public static String buildHtmlContent(Set<User> joinUsers) {

        StringBuilder sb = new StringBuilder(
                "<html>\n").append(
                "<head>\n").append(

                "    <meta charset=\"UTF-8\">\n").append(
                "    <title>参加活动详细人员名单</title>\n").append(

                "</head>\n").append(
                "<body>\n").append(

                "<h3 align=\"center\">参加活动详细人员名单</h3>\n").append(
                "<table style=\"BORDER-COLLAPSE: collapse;width: 98%\" borderColor=#ccc" +

                        " cellSpacing=0 width=300 align=center\n").append(
                "       bgColor=#ffffff\n").append(
                "       border=1>\n").append(
                "    <tbody>\n").append(
                "    <tr>\n").append(

                "        <td>\n").append(
                "            <div align=center>姓名</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>性别</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>职位</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>毕业学校</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>专业</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>年级</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>公司</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>联系电话</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>邮箱</div>\n").append(
                "        </td>\n").append(
                "        <td>\n").append(
                "            <div align=center>微信</div>\n").append(
                "        </td>\n").append(
                "    </tr>");

        for (User user : joinUsers) {
            String sex = "男";
            if (user.getAlumnus().getSex() == 0) {
                sex = "女";
            }
            sb.append("    <tr>\n").append(
                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getRealName()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(sex).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getTitle()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getSchool()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getDepartment()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getGrade()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getCompany()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getPhone()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getEmail()).append("</div>\n").append(
                    "        </td>\n").append(

                    "        <td>\n").append(
                    "            <div align=center>").append(user.getAlumnus().getWeixin()).append("</div>\n").append(
                    "        </td>\n").append(
                    "    </tr>");
        }
        sb.append("    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
        return sb.toString();
    }
}
