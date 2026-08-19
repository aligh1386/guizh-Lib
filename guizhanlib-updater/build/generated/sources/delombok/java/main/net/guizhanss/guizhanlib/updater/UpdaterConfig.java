package net.guizhanss.guizhanlib.updater;

/**
 * This class contains config options for {@link GuizhanBuildsUpdater},
 * which is passed as an argument.
 *
 * @author ybw0014
 * @see GuizhanBuildsUpdater
 */
public final class UpdaterConfig {
    public static final UpdaterConfig DEFAULT = new UpdaterConfig();
    /**
     * Limits the updater to check update only,
     * no file download.
     */
    private boolean checkOnly;
    /**
     * The base URL of Guizhan Builds.
     */
    private String baseUrl;

    private static boolean $default$checkOnly() {
        return false;
    }

    private static String $default$baseUrl() {
        return "https://builds.guizhanss.com/";
    }


    public static class UpdaterConfigBuilder {
        private boolean checkOnly$set;
        private boolean checkOnly$value;
        private boolean baseUrl$set;
        private String baseUrl$value;

        UpdaterConfigBuilder() {
        }

        /**
         * Limits the updater to check update only,
         * no file download.
         * @return {@code this}.
         */
        public UpdaterConfig.UpdaterConfigBuilder checkOnly(final boolean checkOnly) {
            this.checkOnly$value = checkOnly;
            checkOnly$set = true;
            return this;
        }

        /**
         * The base URL of Guizhan Builds.
         * @return {@code this}.
         */
        public UpdaterConfig.UpdaterConfigBuilder baseUrl(final String baseUrl) {
            this.baseUrl$value = baseUrl;
            baseUrl$set = true;
            return this;
        }

        public UpdaterConfig build() {
            boolean checkOnly$value = this.checkOnly$value;
            if (!this.checkOnly$set) checkOnly$value = UpdaterConfig.$default$checkOnly();
            String baseUrl$value = this.baseUrl$value;
            if (!this.baseUrl$set) baseUrl$value = UpdaterConfig.$default$baseUrl();
            return new UpdaterConfig(checkOnly$value, baseUrl$value);
        }

        @Override
        public String toString() {
            return "UpdaterConfig.UpdaterConfigBuilder(checkOnly$value=" + this.checkOnly$value + ", baseUrl$value=" + this.baseUrl$value + ")";
        }
    }

    public static UpdaterConfig.UpdaterConfigBuilder builder() {
        return new UpdaterConfig.UpdaterConfigBuilder();
    }

    /**
     * Limits the updater to check update only,
     * no file download.
     */
    public boolean checkOnly() {
        return this.checkOnly;
    }

    /**
     * The base URL of Guizhan Builds.
     */
    public String baseUrl() {
        return this.baseUrl;
    }

    /**
     * Limits the updater to check update only,
     * no file download.
     * @return {@code this}.
     */
    public UpdaterConfig checkOnly(final boolean checkOnly) {
        this.checkOnly = checkOnly;
        return this;
    }

    /**
     * The base URL of Guizhan Builds.
     * @return {@code this}.
     */
    public UpdaterConfig baseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdaterConfig)) return false;
        final UpdaterConfig other = (UpdaterConfig) o;
        if (this.checkOnly() != other.checkOnly()) return false;
        final Object this$baseUrl = this.baseUrl();
        final Object other$baseUrl = other.baseUrl();
        if (this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.checkOnly() ? 79 : 97);
        final Object $baseUrl = this.baseUrl();
        result = result * PRIME + ($baseUrl == null ? 43 : $baseUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UpdaterConfig(checkOnly=" + this.checkOnly() + ", baseUrl=" + this.baseUrl() + ")";
    }

    public UpdaterConfig() {
        this.checkOnly = UpdaterConfig.$default$checkOnly();
        this.baseUrl = UpdaterConfig.$default$baseUrl();
    }

    /**
     * Creates a new {@code UpdaterConfig} instance.
     *
     * @param checkOnly Limits the updater to check update only,
     * no file download.
     * @param baseUrl The base URL of Guizhan Builds.
     */
    public UpdaterConfig(final boolean checkOnly, final String baseUrl) {
        this.checkOnly = checkOnly;
        this.baseUrl = baseUrl;
    }
}
