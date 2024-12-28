import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.*;
// 92334. 신고 결과 받기
public class GetReports_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        Users users = IntStream.range(0, id_list.length)
                .mapToObj(i -> new User(i, new UserId(id_list[i])))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Users::new));

        Reports reports = Arrays.stream(report)
                .map(rawReport -> ReportParser.parse(users, rawReport))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Reports::new));

        Mails mails = reports.generateMails(users, k);

        return users.findSortedAll().stream()
                .mapToInt(user -> mails.findAllByUser(user).size())
                .toArray();
    }


    private static class Mails {
        private final List<Mail> mails;

        public Mails(List<Mail> mails) {
            this.mails = mails;
        }

        public List<Mail> findAllByUser(User user) {
            return mails.stream().filter(mail -> mail.isSameUser(user))
                    .collect(Collectors.toList());
        }
    }

    /**
     * 정지 사실 알림 메일 클래스
     */
    private static class Mail {
        private final User recipient;

        public Mail(User recipient) {
            this.recipient = recipient;
        }

        public boolean isSameUser(User user) {
            return Objects.equals(recipient, user);
        }
    }


    /**
     * report 배열을 받아 쪼개서 Report 객체를 생성하는 클래스
     */
    private static class ReportParser {
        private static final String DELIMITER = " ";
        public static Report parse(Users users, String report) {
            String[] array = report.split(DELIMITER);
            User reporter = users.findUser(new UserId(array[0]));
            User reported = users.findUser(new UserId(array[1]));

            return new Report(reporter, reported);
        }
    }

    private static class Reports {
        private final List<Report> reports;

        public Reports(List<Report> reports) {
            this.reports = reports;
        }

        public Mails generateMails(Users users, int mailThreshold) {
            return users.findSortedAll().stream()
                    .map(user -> generateMailsOf(user, mailThreshold))
                    .flatMap(Collection::stream)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Mails::new));
        }

        private List<Mail> generateMailsOf(User user, int mailThreshold) {
            List<Report> userReports = reports.stream()
                    .filter(report -> report.isReported(user))
                    .distinct()
                    .collect(Collectors.toList());

            if (userReports.size() >= mailThreshold) {
                return userReports.stream()
                        .map(Report::getReporter)
                        .map(Mail::new)
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }
    }


    /**
     * 신고 클래스 (신고한 사람, 신고 당한 사람)
     */
    private static class Report {
        private final User reporter;
        private final User reported;

        public Report(User reporter, User reported) {
            this.reporter = reporter;
            this.reported = reported;
        }

        public boolean isReported(User user) {
            return reported.equals(user);
        }

        public User getReporter() {
            return reporter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Report report = (Report) o;
            return Objects.equals(reporter, report.reporter) && Objects.equals(reported, report.reported);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reporter, reported);
        }
    }

    /**
     *
     */
    private static class Users {
        private final List<User> users;

        public Users(List<User> users) {
            this.users = users;
        }

        public User findUser(UserId ui) {
            return users.stream()
                    .filter(user -> user.getId().equals(ui))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("User Not Found. Id : " + ui));
        }

        public List<User> findSortedAll() {
            return users.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    /**
     * 사용자를 아이디로 비교할 수 있도록 만든 클래스
     */
    private static class User implements Comparable<User> {
        private final Integer sequence;
        private final UserId userId;

        public User(Integer sequence, UserId userId) {
            this.sequence = sequence;
            this.userId = userId;
        }

        public UserId getId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(userId, user.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId);
        }

        @Override
        public int compareTo(User o) {
            return this.sequence.compareTo(o.sequence);
        }
    }

    /**
     * 사용자 ID 클래스
     */
    private static class UserId {
        private final String id;

        public UserId(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserId userId = (UserId) o;
            return Objects.equals(id, userId.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Id{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }

    @Test
    public void test() {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        Assert.assertArrayEquals(new int[]{2,1,1,0}, solution(id_list, report, k));
    }
}
