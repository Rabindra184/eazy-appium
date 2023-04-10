
package com.github.parse.parser;



import static com.github.parse.utils.StringUtils.interpolate;

import java.io.FileInputStream;
import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.ScalarNode;

public class YamlDataSource implements IDataSource {
    @SneakyThrows
    @Override
    public <T> T parse (final String path, final Class<T> dataClass) {
        try (final FileInputStream in = new FileInputStream (path)) {
            LoaderOptions options = new LoaderOptions();
            options.setAllowDuplicateKeys(true);
            options.setMaxAliasesForCollections(50);
            final Constructor constructor = new Constructor (dataClass, options) {
                @Override
                protected String constructScalar (final ScalarNode node) {
                    return interpolate (node.getValue ());
                }
            };
            final PropertyUtils propertyUtils = new PropertyUtils () {
                @Override
                public Property getProperty (final Class<?> obj, final String name) {
                    String propertyName = name;
                    if (propertyName.indexOf ('_') > -1) {
                        propertyName = CaseFormat.LOWER_UNDERSCORE.to (CaseFormat.LOWER_CAMEL, propertyName);
                    }
                    return super.getProperty (obj, propertyName);
                }
            };
            constructor.setPropertyUtils (propertyUtils);
            final Yaml yaml = new Yaml (constructor);
            return yaml.load (in);
        }
    }
}