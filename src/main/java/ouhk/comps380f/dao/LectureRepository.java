package ouhk.comps380f.dao;

import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Lecture;
import java.util.List;

/**
 *
 * @author German
 */
public interface LectureRepository {
    public int createLecture(Lecture lecture);
    public void createAttachment(Lecture lecture, int topicId);
    public List<Lecture> findAll();
    public Lecture findByLectureId(int id);
    public void deleteByLectureId(int id);
}
