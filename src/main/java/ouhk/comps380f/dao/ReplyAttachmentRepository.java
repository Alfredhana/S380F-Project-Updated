package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.ReplyAttachment;


public interface ReplyAttachmentRepository extends JpaRepository<ReplyAttachment, Long> {
    
    public ReplyAttachment findByReplyidAndRname(long replyid, String rname);
    
}
