/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.tasks;

import org.gradle.api.specs.Spec;
import org.gradle.internal.scan.UsedByScanPlugin;

@UsedByScanPlugin("doesn't link against this type, but expects these values - See ExecuteTaskBuildOperationType")
public enum TaskOutputCachingDisabledReasonCategory {
    /**
     * Reason for disabled caching is not known.
     */
    UNKNOWN,

    /**
     * Caching has not been enabled for the build.
     */
    BUILD_CACHE_DISABLED,

    /**
     * Caching has not been enabled for the task.
     */
    NOT_ENABLED_FOR_TASK,

    /**
     * The task has no outputs declared.
     */
    NO_OUTPUTS_DECLARED,

    /**
     * Task has outputs declared via {@literal @}{@link org.gradle.api.tasks.OutputFiles} or {@literal @}{@link org.gradle.api.tasks.OutputDirectories}.
     *
     * @deprecated Simply having plural outputs is not a reason anymore to disable caching since Gradle 5.0
     * The enum cannot be removed as build scan plugin depends on it.
     */
    @Deprecated
    PLURAL_OUTPUTS,

    /**
     * Task has a {@link org.gradle.api.file.FileTree} or {@link org.gradle.api.internal.file.collections.DirectoryFileTree} as an output.
     *
     * @since 5.0
     */
    NON_CACHEABLE_TREE_OUTPUT,

    /**
     * Caching is disabled for the task via {@link org.gradle.api.tasks.TaskOutputs#cacheIf(Spec)}.
     */
    CACHE_IF_SPEC_NOT_SATISFIED,

    /**
     * Caching is disabled for the task via {@link org.gradle.api.tasks.TaskOutputs#doNotCacheIf(String, Spec)}.
     */
    DO_NOT_CACHE_IF_SPEC_SATISFIED,

    /**
     * Task's outputs overlap with another task. As Gradle cannot safely determine which task each output file belongs to it disables caching.
     */
    OVERLAPPING_OUTPUTS,

    /**
     * The generated build cache key is invalid. Reasons for invalid cache keys:
     * <ul>
     *     <li>some type used as an input to the task is loaded via a custom classloader Gradle wasn't able to track,</li>
     *     <li>a Java lambda was used as an input (see https://github.com/gradle/gradle/issues/5510).</li>
     * </ul>
     */
    INVALID_BUILD_CACHE_KEY
}
