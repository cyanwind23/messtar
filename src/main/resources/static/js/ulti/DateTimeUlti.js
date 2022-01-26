export class DateTimeUlti {
    static toDateTime(milis) {
        return new Date(milis);
    }
    static toString(dateTime) {
        return dateTime.toLocaleDateString("en-GB");
    }
}