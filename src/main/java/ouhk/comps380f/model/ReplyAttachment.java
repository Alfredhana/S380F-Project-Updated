package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ReplyAttachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "filename")
    private String rname;

    @Column(name = "content_type")
    private String rmimeContentType;

    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] rcontents;
    
    @Column(name = "reply_id", insertable = false, updatable = false)
    private long replyid;
    
    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRmimeContentType() {
        return rmimeContentType;
    }

    public void setRmimeContentType(String rmimeContentType) {
        this.rmimeContentType = rmimeContentType;
    }

    public byte[] getRcontents() {
        return rcontents;
    }

    public void setRcontents(byte[] rcontents) {
        this.rcontents = rcontents;
    }

    

    public long getReplyid() {
        return replyid;
    }

    public void setReplyid(long replyid) {
        this.replyid = replyid;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }    
}
