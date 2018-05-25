package com.dao;

import com.model.Member;
import com.tools.ChStr;
import com.tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberImpl implements MemberDao {

    private ConnDB conn = new ConnDB();
    private ChStr chStr = new ChStr();

    public int insert(Member m) {
        int ret = -1;
        if (m.getUsername() != null) {
            String sql = "INSERT INTO tb_member(userName, trueName, password, city, address, postcode, cardNo, cardType, tel, email) VALUE('"
                    + chStr.chStr(m.getUsername()) + "','"
                    + chStr.chStr(m.getTruename()) + "','"
                    + chStr.chStr(m.getPwd()) + "','"
                    + chStr.chStr(m.getCity()) + "','"
                    + chStr.chStr(m.getAddress()) + "','"
                    + chStr.chStr(m.getPostcode()) + "','"
                    + chStr.chStr(m.getCardno()) + "','"
                    + chStr.chStr(m.getCardtype()) + "','"
                    + chStr.chStr(m.getTel())+"','"
                    + chStr.chStr(m.getEmail())+"')";
            return conn.executeUpdate(sql);
        }else {
            ret=0;
        }
        conn.close();
        return ret;
    }


    public List select() {

        Member form = null;
        List list = new ArrayList();
        String sql = "select * from tb_member";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()){
                form = new Member();
                form.setID(Integer.valueOf(rs.getString(1)));
                list.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
}
