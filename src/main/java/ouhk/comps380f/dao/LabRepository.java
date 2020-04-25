package ouhk.comps380f.dao;

import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Lab;
import java.util.List;

/**
 *
 * @author German
 */
public interface LabRepository {
    public int createLab(Lab lab);
    public void createAttachment(Lab lab, int topicId);
    public List<Lab> findAll();
    public Lab findByLabId(int id);
    public void deleteByLabId(int id);
}
