package app.commons;

public enum AppMessages {

    USER_EXISTS("user_exists"),
    USER_NOT_FOUND("user_not_found"),
    USER_SAVE_SUCCESS("user_save_success"),
    USER_UPDATE_SUCCESS("user_update_success"),
    USER_DELETE_SUCCESS("user_delete_success"),

    PRECAUTION_NOT_FOUND("precaution_not_found"),
    PRECAUTION_SAVE_SUCCESS("precaution_save_success"),
    PRECAUTION_UPDATE_SUCCESS("precaution_update_success"),
    PRECAUTION_DELETE_SUCCESS("precaution_delete_success"),

    EMAIL_EXISTS("email_exists");


    private String message;

    private AppMessages(String message){
        this.message = message;
    }

    public String getMessage(){ return message; }

}
