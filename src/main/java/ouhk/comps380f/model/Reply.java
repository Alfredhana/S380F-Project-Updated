package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
    private long reply_id;
    
    private String reply_content;
    private String reply_author;
    //private long ticket_id;
    
    @OneToMany(mappedBy = "reply", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<ReplyAttachment> replyAttachments = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    
    /*public Reply(){        
    }

    public Reply(String reply_content, String reply_author, long ticket_id) {
        this.reply_id = reply_id;
        this.reply_content = reply_content;
        this.reply_author = reply_author;
        this.ticket_id = ticket_id;
    }*/

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public long getReply_id() {
        return reply_id;
    }

    public void setReply_id(long reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_author() {
        return reply_author;
    }

    public void setReply_author(String reply_author) {
        this.reply_author = reply_author;
    }    
    
    /*public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }*/

    public List<ReplyAttachment> getReplyAttachments() {
        return replyAttachments;
    }

    public void setReplyAttachments(List<ReplyAttachment> replyAttachments) {
        this.replyAttachments = replyAttachments;
    }
}
