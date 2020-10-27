package jenkins.mvn;

import hudson.FilePath;
import hudson.model.AbstractBuild;

public class filePathGetter {

    public FilePath getFilePath(AbstractBuild<?, ?> build, String targetPath) {
        FilePath mrSettings = build.getModuleRoot().child(targetPath);
        FilePath wsSettings = build.getWorkspace().child(targetPath);
        try {
            if (!wsSettings.exists() && mrSettings.exists()) {
                wsSettings = mrSettings;
            }
        } catch (Exception e) {
            throw new IllegalStateException("failed to find settings.xml at: " + wsSettings.getRemote());
        }
        return wsSettings;
    }
}
