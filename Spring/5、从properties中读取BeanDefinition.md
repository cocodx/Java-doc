![image](../images/Snipaste_2022-03-12_02-57-26.png)

```properties
employee.(class)=MyClass       // bean is of class MyClass
employee.(abstract)=true       // this bean can't be instantiated directly
employee.group=Insurance       // real property
employee.usesDialUp=false      // real property (potentially overridden)

salesrep.(parent)=employee     // derives from "employee" bean definition
salesrep.(lazy-init)=true      // lazily initialize this singleton bean
salesrep.manager(ref)=tony     // reference to another bean
salesrep.department=Sales      // real property

techie.(parent)=employee       // derives from "employee" bean definition
techie.(scope)=prototype       // bean is a prototype (not a shared instance)
techie.manager(ref)=jeff       // reference to another bean
techie.department=Engineering  // real property
techie.usesDialUp=true         // real property (overriding parent value)

ceo.$0(ref)=secretary          // inject 'secretary' bean as 0th constructor arg
ceo.$1=1000000  
```

```java
public static final String TRUE_VALUE = "true";

	/**
	 * Separator between bean name and property name.
	 * We follow normal Java conventions.
	 */
	public static final String SEPARATOR = ".";

	/**
	 * Special key to distinguish {@code owner.(class)=com.myapp.MyClass}.
	 */
	public static final String CLASS_KEY = "(class)";

	/**
	 * Special key to distinguish {@code owner.(parent)=parentBeanName}.
	 */
	public static final String PARENT_KEY = "(parent)";

	/**
	 * Special key to distinguish {@code owner.(scope)=prototype}.
	 * Default is "true".
	 */
	public static final String SCOPE_KEY = "(scope)";

	/**
	 * Special key to distinguish {@code owner.(singleton)=false}.
	 * Default is "true".
	 */
	public static final String SINGLETON_KEY = "(singleton)";

	/**
	 * Special key to distinguish {@code owner.(abstract)=true}
	 * Default is "false".
	 */
	public static final String ABSTRACT_KEY = "(abstract)";

	/**
	 * Special key to distinguish {@code owner.(lazy-init)=true}
	 * Default is "false".
	 */
	public static final String LAZY_INIT_KEY = "(lazy-init)";

	/**
	 * Property suffix for references to other beans in the current
	 * BeanFactory: e.g. {@code owner.dog(ref)=fido}.
	 * Whether this is a reference to a singleton or a prototype
	 * will depend on the definition of the target bean.
	 */
	public static final String REF_SUFFIX = "(ref)";

	/**
	 * Prefix before values referencing other beans.
	 */
	public static final String REF_PREFIX = "*";

	/**
	 * Prefix used to denote a constructor argument definition.
	 */
	public static final String CONSTRUCTOR_ARG_PREFIX = "$";
```