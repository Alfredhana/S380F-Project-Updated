package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Reply implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private long replyid;
    
    @Column(name = "reply_content")
    private String replycontent;
    
    @Column(name = "reply_author")
    private String replyauthor;
    
    @OneToMany(mappedBy = "reply", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<ReplyAttachment> replyAttachments = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public long getReplyid() {
        return replyid;
    }

    public void setReplyid(long replyid) {
        this.replyid = replyid;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

    public String getReplyauthor() {
        return replyauthor;
    }

    public void setReplyauthor(String replyauthor) {
        this.replyauthor = replyauthor;
    }    

    public List<ReplyAttachment> getReplyAttachments() {
        return replyAttachments;
    }

    public void setReplyAttachments(List<ReplyAttachment> replyAttachments) {
        this.replyAttachments = replyAttachments;
    }
}
