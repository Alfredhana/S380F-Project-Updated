package ouhk.comps380f.service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.ReplyAttachmentRepository;
import ouhk.comps380f.model.ReplyAttachment;


public class ReplyAttachmentServiceImpl implements ReplyAttachmentService {
    
    @Resource
    private ReplyAttachmentRepository replyAttachmentRepo;
    
    @Override
    @Transactional
    public ReplyAttachment getReplyAttachment(long replyId, String name){
        return replyAttachmentRepo.findByTicketIdAndName(replyId, name);
    }
}
