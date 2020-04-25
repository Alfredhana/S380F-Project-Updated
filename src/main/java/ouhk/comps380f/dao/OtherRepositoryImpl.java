/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Other;
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
public class OtherRepositoryImpl implements OtherRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    protected class ItemMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Other other = new Other();
            other.setId(rs.getInt("topic_id"));
            other.setSubject(rs.getString("topic_title"));
            return other;
        }
    }

    /**
     *
     * @param other
     */
    @Override
    public int createOther(final Other other) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("insert into topic (topic_title, topic_content, topic_author, topic_category) values (?, ?, ?, 'other')", new String[]{"topic_id"});
                ps.setString(1, other.getSubject());
                ps.setString(2, other.getBody());
                ps.setString(3, other.getCustomerName());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void createAttachment(Other other, int topicId) {
        for (Attachment attachment : other.getAttachments()) {
            jdbcOp.update("insert into attachments (name, content, mime, topic_id) values (?, ?, ?, ?)", attachment.getName(), attachment.getContents(), attachment.getMimeContentType(), topicId);
        }
    }

    @Override
    public List<Other> findAll() {
        List<Other> others = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT topic_id, topic_title FROM topic WHERE topic_category = ? ", "other");
        //String sql = "SELECT  topic_id, topic_title, topic_content, topic_author FROM topic";  
        for (Map<String, Object> row : rows) {
            Other other = new Other();
            other.setId((int) row.get("topic_id"));
            other.setSubject((String) row.get("topic_title"));
            others.add(other);
        }
        return others;

    }

    @Override
    public Other findByOtherId(int id) {
        Other other = new Other();
        List<Map<String, Object>> rows = jdbcOp.queryForList("SELECT * FROM topic where topic_id = ? ", id);
        for (Map<String, Object> row : rows) {
            other.setId((int) row.get("topic_id"));
            other.setSubject((String) row.get("topic_subject"));
            other.setCustomerName((String) row.get("topic_author"));
            other.setBody((String) row.get("topic_content"));
        }
        //System.out.println(other.getBody());
        // System.out.println(other.getSubject());
        List<Map<String, Object>> attachmentRows = jdbcOp.queryForList("SELECT * FROM attachments where topic_id = ? ", id);
        for (Map<String, Object> attachmentRow : attachmentRows) {
            Attachment attachment = new Attachment();
            attachment.setName((String) attachmentRow.get("name"));
            attachment.setContents((byte[]) attachmentRow.get("content"));
            attachment.setMimeContentType((String) attachmentRow.get("mime"));

            other.addAttachment(attachment);
        }
        return other;
    }

    @Override
    public void deleteByOtherId(int id) {
        jdbcOp.update("delete from attachments where topic_id = ?", id);
        jdbcOp.update("delete from reply where topic_id = ?", id);
        jdbcOp.update("delete from topic where topic_id = ?", id);
    }
}
