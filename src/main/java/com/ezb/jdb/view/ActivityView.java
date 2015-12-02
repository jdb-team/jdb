package com.ezb.jdb.view;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.User;
import org.joda.time.DateTime;

import java.util.Set;

/**
 * 活动视图包装
 * author : liufeng
 * create time:2015/8/10 11:41
 */
public class ActivityView {

    public static String convert2Json(PageResult<Activity> pageResult, User user) {
        for (Activity activity : pageResult.getResultList()) {
            convert2Json(activity, user);
        }
        return ResponseData.getResData(pageResult);
    }

    public static Activity convert2Json(Activity activity, User user) {
        if (null == activity.getJoinUsers()
                || null != activity.getJoinUsers()
                && activity.getJoinUsers().contains(user)) {
            activity.setJoin(0);
        } else {
            activity.setJoin(1);
        }
        activity.setTimeState(getState(activity));
        return activity;
    }

    /**
     * 1 报名中 2 已满员 3 已截止 4 进行中 5 已结束
     *
     * @param activity
     * @return
     */
    public static Integer getState(Activity activity) {
        DateTime nowTime = DateTime.now();
        if (nowTime.isAfter(new DateTime(activity.getCloseTime()))) {
            return 3;
        }
        if (nowTime.isAfter(new DateTime(activity.getStartTime()))
                && nowTime.isBefore(new DateTime(activity.getEndTime()))) {
            return 4;
        }
        if (nowTime.isAfter(new DateTime(activity.getEndTime()))) {
            return 5;
        }
        if (activity.getJoinUsers() == null ||
                activity.getJoinUsers().size() < activity.getPersonLimit()) {
            return 1;
        }
        if (activity.getJoinUsers().size() >= activity.getPersonLimit()) {
            return 2;
        }
        return -1;
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
