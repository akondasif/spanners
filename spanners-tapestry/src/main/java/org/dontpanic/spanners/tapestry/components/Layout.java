package org.dontpanic.spanners.tapestry.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;

/**
 * Layout component for pages of application spanners-tapestry.
 */
@IncludeStylesheet({"context:layout/layout.css", "context:layout/messages.css"})
public class Layout
{
    /** The page title, for the <title> element and the <h1> element. */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    public String getClassForPageName()
    {
      return resources.getPageName().equalsIgnoreCase(pageName)
             ? "current_page_item"
             : null;
    }

    public String[] getPageNames()
    {
      return new String[] { "Index", "CreateSpanner"};
    }
}
