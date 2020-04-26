package ouhk.comps380f.dao;

import ouhk.comps380f.model.ReplyAttachment;


public interface ReplyAttachmentRepository {
    
    public ReplyAttachment findByTicketIdAndName(long replyId, String name);
    
}
