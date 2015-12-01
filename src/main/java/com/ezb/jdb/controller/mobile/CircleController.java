package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.CirCmt;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.JoinUserCircle;
import com.ezb.jdb.service.ICirCmtService;
import com.ezb.jdb.service.ICircleService;
import com.ezb.jdb.service.IJoinUserCircleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 14:56
 */
@Controller
public class CircleController {

    @Resource
    private ICircleService circleServiceImpl;

    @Resource
    private ICirCmtService cirCmtServiceImpl;

    @Resource
    private IJoinUserCircleService joinUserCircleServiceImpl;

    /**
     * 创建/更新圈子
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/circle/save")
    public
    @ResponseBody
    String save(HttpServletRequest request, String phone, Circle circle) {
        return circleServiceImpl.save(request, phone, circle);
    }

    /**
     * 圈子分页列表
     *
     * @param pageResult
     * @param phone
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/circle/querycircles")
    public
    @ResponseBody
    String queryCircles(PageResult<Circle> pageResult, String phone, String queryWords) {
        return circleServiceImpl.queryCircles(pageResult, phone, queryWords);
    }

    /**
     * 我的圈子列表
     *
     * @param pageResult
     * @param phone      当前用户手机号
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/circle/querymycircles")
    public
    @ResponseBody
    String queryMyCircles(PageResult<Circle> pageResult, String phone, String queryWords) {
        return circleServiceImpl.queryMyCircles(pageResult, phone, queryWords);
    }

    /**
     * 圈子评论列表
     *
     * @param pageResult
     * @param circleid
     * @return
     */
    @RequestMapping(value = "mobile/circle/querycircmt")
    public
    @ResponseBody
    String queryCircmt(PageResult<CirCmt> pageResult, String circleid) {
        return ResponseData.getResData(cirCmtServiceImpl.queryCircmts(pageResult, circleid));
    }

    /**
     * 圈子成员列表
     *
     * @param pageResult
     * @param circleid
     * @return
     */
    @RequestMapping(value = "mobile/circle/querymembers")
    public
    @ResponseBody
    String queryMembers(PageResult<JoinUserCircle> pageResult, String circleid) {
        return ResponseData.getResData(joinUserCircleServiceImpl.queryMembers(pageResult, circleid));
    }

    /**
     * 创建圈子评论
     *
     * @return
     */
    @RequestMapping(value = "mobile/circle/createcircmt")
    public
    @ResponseBody
    String createCircmt(String phone, CirCmt cirCmt) {
        return cirCmtServiceImpl.createCircmt(phone, cirCmt);
    }

    /**
     * 加入圈子
     *
     * @param phone 手机号码
     * @param id    圈子id
     * @return
     */
    @RequestMapping(value = "mobile/circle/join")
    public
    @ResponseBody
    String join(String phone, Integer id) {
        return circleServiceImpl.join(phone, id);
    }

    /**
     * 更新昵称
     *
     * @param phone    当前用户phone
     * @param cid      用户圈子关系id
     * @param nickName 昵称
     * @return
     */
    @RequestMapping(value = "mobile/circle/nickname")
    public
    @ResponseBody
    String nickname(String phone, Integer cid, String nickName) {
        return circleServiceImpl.saveNickName(phone, cid, nickName);
    }

    /**
     * 查询昵称
     *
     * @param phone 当前用户id
     * @param cid   用户圈子关系id
     * @return
     */
    @RequestMapping(value = "mobile/circle/viewnickname")
    public
    @ResponseBody
    String viewnickname(String phone, Integer cid) {
        return circleServiceImpl.viewnickname(phone, cid);
    }

    /**
     * 查看圈子详情
     *
     * @param phone 手机号
     * @param cid   圈子id
     * @return
     */
    @RequestMapping(value = "mobile/circle/viewcircle")
    public
    @ResponseBody
    String viewCircle(String phone, Integer cid) {
        return circleServiceImpl.viewCircle(phone, cid);
    }

    /**
     * 解散圈子
     *
     * @return
     */
    @RequestMapping(value = "mobile/circle/delete")
    public
    @ResponseBody
    String delete(String phone, Integer id) {
        return circleServiceImpl.delete(phone, id);
    }

    /**
     * 批量添加圈子
     *
     * @return
     */
    @RequestMapping(value = "mobile/circle/batchjoin")
    public
    @ResponseBody
    String batchJoin(String phones, int circleId) {
        return circleServiceImpl.batchJoin(phones, circleId);
    }

    /**
     * 退出圈子
     *
     * @param phone 手机号码
     * @param id    圈子id
     * @return
     */
    @RequestMapping(value = "mobile/circle/exit")
    public
    @ResponseBody
    String exit(String phone, Integer id) {
        return circleServiceImpl.exit(phone, id);
    }
}
