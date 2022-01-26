export class DateTimeUtil {
    static toDateTime(millis) {
        return new Date(millis);
    }
    static toString(dateTime) {
        return dateTime.toLocaleDateString("en-GB");
    }
}