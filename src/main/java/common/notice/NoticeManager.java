package common.notice;

import java.util.HashMap;
import java.util.Map;

import game.NoticeTable;

class NoticeData {

}

public abstract class NoticeManager {
    public Map<NoticeTable, NoticeData> noticeMap = new HashMap();

    public abstract void registerNotice();

    public void addNotice(NoticeTable noticeTable, NoticeData dNoticeData) {
        this.noticeMap.put(noticeTable, dNoticeData);
    }

    public void removeNotice(NoticeTable noticeTable) {
        this.noticeMap.remove(noticeTable);
    }

    public boolean haveNotice(NoticeTable notice) {
        return noticeMap.containsKey(notice);
    }

    public NoticeData GetNotice(NoticeTable noticeTable) {
        return noticeMap.get(noticeTable);
    }
}
