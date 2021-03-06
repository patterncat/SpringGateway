/*
 * Copyright 2013 Netflix, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package filters.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.apache.http.util.TextUtils

/**
 * @author mhawthorne
 */
class PreDecorationFilter extends ZuulFilter {

    @Override
    int filterOrder() {
        return 5
    }

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    boolean shouldFilter() {
        if (TextUtils.isEmpty(RequestContext.getCurrentContext().getRequest().getHeader("OwnerId"))) {
            return false
        }
        return true
    }

    @Override
    Object run() {
        RequestContext ctx = RequestContext.getCurrentContext()
        if (ctx.getRequest().getHeader("OrgId").equals("123456")) {
            ctx.setRouteHost(new URL("http://testebapi.51pms.net:8001/"))
        } else {
// sets origin
            ctx.setRouteHost(new URL("http://ebapi.51pms.net:8001/"))
        }

        // sets custom header to send to the origin
        ctx.addOriginResponseHeader("cache-control", "max-age=3600");
    }

}
