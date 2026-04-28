package ma.ensi.projettutore.util;

public class Constants {

    public static final String DEFAULT_ROLE = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    public static final String FILE_UPLOAD_DIR = "uploads/";
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    public static final String QUIZ_RESULT_PASSED = "PASSED";
    public static final String QUIZ_RESULT_FAILED = "FAILED";

    public static final String RECOMMENDATION_TYPE_ACADEMIC = "ACADEMIC";
    public static final String RECOMMENDATION_TYPE_PROFESSIONAL = "PROFESSIONAL";
    public static final String RECOMMENDATION_TYPE_PERSONAL = "PERSONAL";

    public static final String JOB_CATEGORY_IT = "IT";
    public static final String JOB_CATEGORY_ENGINEERING = "ENGINEERING";
    public static final String JOB_CATEGORY_BUSINESS = "BUSINESS";
    public static final String JOB_CATEGORY_EDUCATION = "EDUCATION";

    public static final String SCHOOL_DOMAIN_COMPUTER_SCIENCE = "Computer Science";
    public static final String SCHOOL_DOMAIN_ENGINEERING = "Engineering";
    public static final String SCHOOL_DOMAIN_BUSINESS = "Business";
    public static final String SCHOOL_DOMAIN_MEDICINE = "Medicine";

    public static final String PROFILE_INTEREST_TECHNOLOGY = "Technology";
    public static final String PROFILE_INTEREST_BUSINESS = "Business";
    public static final String PROFILE_INTEREST_RESEARCH = "Research";
    public static final String PROFILE_INTEREST_ARTS = "Arts";

    public static final String PREFERRED_WORK_REMOTE = "REMOTE";
    public static final String PREFERRED_WORK_HYBRID = "HYBRID";
    public static final String PREFERRED_WORK_ONSITE = "ONSITE";

    private Constants() {
        throw new UnsupportedOperationException("Utility class");
    }
}