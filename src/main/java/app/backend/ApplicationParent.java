package app.backend;

import app.Application;

public class ApplicationParent {
    private Application application;

    public ApplicationParent(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
