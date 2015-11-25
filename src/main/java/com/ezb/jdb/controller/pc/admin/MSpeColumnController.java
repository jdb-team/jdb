package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.SpeColumn;
import com.ezb.jdb.service.ISpeColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 专栏
 * author : liufeng
 * create time:2015/11/13 11:44
 */
@Controller
public class MSpeColumnController {

    @Resource
    private ISpeColumnService speColumnServiceImpl;

    /**
     * 添加专栏
     *
     * @param speColumn
     * @return
     */
    @RequestMapping(value = "pc/admin/specolumn/add")
    public
    @ResponseBody
    String add(SpeColumn speColumn) {
        return speColumnServiceImpl.add(speColumn);
    }

    /**
     * 更新专栏
     *
     * @param speColumn
     * @return
     */
    @RequestMapping(value = "pc/admin/specolumn/update")
    public
    @ResponseBody
    String update(SpeColumn speColumn) {
        return speColumnServiceImpl.update(speColumn);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/specolumn/delete")
    public
    @ResponseBody
    String delete(String id) {
        return speColumnServiceImpl.delete(id);
    }

    /**
     * 查询
     *
     * @param pageResult 分页信息
     * @param typeName   账号
     * @return
     */
    @RequestMapping(value = "pc/admin/specolumn/query")
    public
    @ResponseBody
    String query(PageResult<SpeColumn> pageResult,
                 String columnName) {
        return speColumnServiceImpl.query(pageResult, columnName);
    }

    /**
     * 查询某个专栏
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/specolumn/view")
    public
    @ResponseBody
    String view(Integer id) {
        return speColumnServiceImpl.queryById(id);
    }
}
