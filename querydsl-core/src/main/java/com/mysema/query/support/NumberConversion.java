/*
 * Copyright 2012, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.support;

import java.util.Collections;
import java.util.List;

import com.mysema.query.types.Expression;
import com.mysema.query.types.ExpressionBase;
import com.mysema.query.types.FactoryExpression;
import com.mysema.query.types.Visitor;
import com.mysema.util.MathUtils;

/**
 * @author tiwe
 *
 * @param <T>
 */
public class NumberConversion<T> extends ExpressionBase<T> implements FactoryExpression<T> {
    
    private static final long serialVersionUID = 7840412008633901748L;

    private final List<Expression<?>> exprs;
    
    public NumberConversion(Expression<T> expr) {
        super(expr.getType());
        exprs = Collections.<Expression<?>>singletonList(expr);
    }
    
    @Override
    public <R, C> R accept(Visitor<R, C> v, C context) {
        return v.visit(this, context);
    }

    @Override
    public List<Expression<?>> getArgs() {
        return exprs;
    }

    @Override
    public T newInstance(Object... args) {
        return (T)MathUtils.cast((Number)args[0], (Class)getType());
    }

}
