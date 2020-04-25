/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Lab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */
@Repository
public class LabRepositoryImpl implements LabRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    protected class ItemMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lab lab = new Lab();
            lab.setId(rs.getInt("topic_id"));
            lab.setSubject(rs.getString("topic_title"));
            return lab;
        }
    }

    /**
     *
     * @param lab
     */
    @Override
    public int createLab(final Lab lab) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("insert into topic (topic_title, topic_content, topic_author, topic_category) values (?, ?, ?, 'lab')", new String[]{"topic_id"});
                ps.setString(1, lab.getSubject());
                ps.setString(2, lab.getBody());
                ps.setString(3, lab.getCustomerName());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void createAttachment(Lab lab, int topicId) {
        for (Attachment attachment : lab.getAttachments()) {
            jdbcOp.update("insert into attachments (name, content, mime, topic_id) values (?, ?, ?, ?)", attachment.getName(), attachment.getContents(), attachment.getMimeContentType(), topicId);
        }
    }

    @Override
    public List<Lab> findAll() {
        List<Lab> labs = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT topic_id, topic_title FROM topic WHERE topic_category = ? ", "lab");
        //String sql = "SELECT  topic_id, topic_title, topic_content, topic_author FROM topic";  
        for (Map<String, Object> row : rows) {
            Lab lab = new Lab();
            lab.setId((int) row.get("topic_id"));
            lab.setSubject((String) row.get("topic_title"));
            labs.add(lab);
        }
        return labs;

    }

    @Override
    public Lab findByLabId(int id) {
        Lab lab = new Lab();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT * FROM topic where topic_id = ? ", id);
        for (Map<String, Object> row : rows) {
            lab.setId((int) row.get("topic_id"));
            lab.setSubject((String) row.get("topic_subject"));
            lab.setCustomerName((String) row.get("topic_author"));
            lab.setBody((String) row.get("topic_content"));
        }
        //System.out.println(lab.getBody());
        // System.out.println(lab.getSubject());
        List<Map<String, Object>> attachmentRows = jdbcOp.queryForList("SELECT * FROM attachments where topic_id = ? ", id);
        for (Map<String, Object> attachmentRow : attachmentRows) {
            Attachment attachment = new Attachment();
            attachment.setName((String) attachmentRow.get("name"));
            attachment.setContents((byte[]) attachmentRow.get("content"));
            attachment.setMimeContentType((String) attachmentRow.get("mime"));

            lab.addAttachment(attachment);
        }
        return lab;
    }

    @Override
    public void deleteByLabId(int id) {
        jdbcOp.update("delete from attachments where topic_id = ?", id);
        jdbcOp.update("delete from reply where topic_id = ?", id);
        jdbcOp.update("delete from topic where topic_id = ?", id);
    }
}
