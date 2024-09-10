// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.cadl.flatten.FlattenServiceVersion;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the FlattenClient type.
 */
public final class FlattenClientImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final FlattenClientService service;

    /**
     */
    private final String endpoint;

    /**
     * Gets.
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Service version.
     */
    private final FlattenServiceVersion serviceVersion;

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public FlattenServiceVersion getServiceVersion() {
        return this.serviceVersion;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     * 
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Initializes an instance of FlattenClient client.
     * 
     * @param endpoint
     * @param serviceVersion Service version.
     */
    public FlattenClientImpl(String endpoint, FlattenServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of FlattenClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint
     * @param serviceVersion Service version.
     */
    public FlattenClientImpl(HttpPipeline httpPipeline, String endpoint, FlattenServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of FlattenClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint
     * @param serviceVersion Service version.
     */
    public FlattenClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint,
        FlattenServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.serviceVersion = serviceVersion;
        this.service = RestProxy.create(FlattenClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for FlattenClient to be used by the proxy service to perform REST calls.
     */
    @Host("{endpoint}/openai")
    @ServiceInterface(name = "FlattenClient")
    public interface FlattenClientService {
        @Post("/flatten/send")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> send(@HostParam("endpoint") String endpoint, @QueryParam("id") String id,
            @QueryParam("constantQueryParam") String constantQueryParam, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Content-Type") String contentType, @BodyParam("application/json") BinaryData sendRequest,
            RequestOptions requestOptions, Context context);

        @Post("/flatten/send")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> sendSync(@HostParam("endpoint") String endpoint, @QueryParam("id") String id,
            @QueryParam("constantQueryParam") String constantQueryParam, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Content-Type") String contentType, @BodyParam("application/json") BinaryData sendRequest,
            RequestOptions requestOptions, Context context);

        @Post("/flatten/send-projected-name")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> sendProjectedName(@HostParam("endpoint") String endpoint, @QueryParam("id") String id,
            @HeaderParam("Content-Type") String contentType,
            @BodyParam("application/json") BinaryData sendProjectedNameRequest, RequestOptions requestOptions,
            Context context);

        @Post("/flatten/send-projected-name")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> sendProjectedNameSync(@HostParam("endpoint") String endpoint, @QueryParam("id") String id,
            @HeaderParam("Content-Type") String contentType,
            @BodyParam("application/json") BinaryData sendProjectedNameRequest, RequestOptions requestOptions,
            Context context);

        @Post("/flatten/send-long")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> sendLong(@HostParam("endpoint") String endpoint, @QueryParam("name") String name,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Content-Type") String contentType,
            @BodyParam("application/json") BinaryData sendLongRequest, RequestOptions requestOptions, Context context);

        @Post("/flatten/send-long")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> sendLongSync(@HostParam("endpoint") String endpoint, @QueryParam("name") String name,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Content-Type") String contentType,
            @BodyParam("application/json") BinaryData sendLongRequest, RequestOptions requestOptions, Context context);

        @Patch("/flatten/patch/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> update(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @PathParam("id") long id,
            @HeaderParam("Accept") String accept, @BodyParam("application/merge-patch+json") BinaryData updateRequest,
            RequestOptions requestOptions, Context context);

        @Patch("/flatten/patch/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> updateSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @PathParam("id") long id,
            @HeaderParam("Accept") String accept, @BodyParam("application/merge-patch+json") BinaryData updateRequest,
            RequestOptions requestOptions, Context context);

        // @Multipart not supported by RestProxy
        @Post("/flatten/upload/{name}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadFile(@HostParam("endpoint") String endpoint, @PathParam("name") String name,
            @HeaderParam("content-type") String contentType,
            @BodyParam("multipart/form-data") BinaryData uploadFileRequest, RequestOptions requestOptions,
            Context context);

        // @Multipart not supported by RestProxy
        @Post("/flatten/upload/{name}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadFileSync(@HostParam("endpoint") String endpoint, @PathParam("name") String name,
            @HeaderParam("content-type") String contentType,
            @BodyParam("multipart/form-data") BinaryData uploadFileRequest, RequestOptions requestOptions,
            Context context);

        // @Multipart not supported by RestProxy
        @Post("/flatten/upload-todo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadTodo(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType,
            @BodyParam("multipart/form-data") BinaryData uploadTodoRequest, RequestOptions requestOptions,
            Context context);

        // @Multipart not supported by RestProxy
        @Post("/flatten/upload-todo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadTodoSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType,
            @BodyParam("multipart/form-data") BinaryData uploadTodoRequest, RequestOptions requestOptions,
            Context context);
    }

    /**
     * The send operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     endpoint: String (Required)
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     constant: String (Required)
     *     requiredInt: int (Required)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param sendRequest The sendRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendWithResponseAsync(String id, BinaryData sendRequest,
        RequestOptions requestOptions) {
        final String constantQueryParam = "constant";
        final String contentType = "application/json";
        return FluxUtil.withContext(context -> service.send(this.getEndpoint(), id, constantQueryParam,
            this.getServiceVersion().getVersion(), contentType, sendRequest, requestOptions, context));
    }

    /**
     * The send operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     endpoint: String (Required)
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     constant: String (Required)
     *     requiredInt: int (Required)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param sendRequest The sendRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendWithResponse(String id, BinaryData sendRequest, RequestOptions requestOptions) {
        final String constantQueryParam = "constant";
        final String contentType = "application/json";
        return service.sendSync(this.getEndpoint(), id, constantQueryParam, this.getServiceVersion().getVersion(),
            contentType, sendRequest, requestOptions, Context.NONE);
    }

    /**
     * The sendProjectedName operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     file_id: String (Required)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param sendProjectedNameRequest The sendProjectedNameRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendProjectedNameWithResponseAsync(String id, BinaryData sendProjectedNameRequest,
        RequestOptions requestOptions) {
        final String contentType = "application/json";
        return FluxUtil.withContext(context -> service.sendProjectedName(this.getEndpoint(), id, contentType,
            sendProjectedNameRequest, requestOptions, context));
    }

    /**
     * The sendProjectedName operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     file_id: String (Required)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param sendProjectedNameRequest The sendProjectedNameRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendProjectedNameWithResponse(String id, BinaryData sendProjectedNameRequest,
        RequestOptions requestOptions) {
        final String contentType = "application/json";
        return service.sendProjectedNameSync(this.getEndpoint(), id, contentType, sendProjectedNameRequest,
            requestOptions, Context.NONE);
    }

    /**
     * The sendLong operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     dataInt: int (Required)
     *     dataIntOptional: Integer (Optional)
     *     dataLong: Long (Optional)
     *     requiredUser (Required): (recursive schema, see requiredUser above)
     *     data_float: Double (Optional)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     _dummy: String (Optional)
     *     constant: String (Required)
     * }
     * }</pre>
     * 
     * @param name The name parameter.
     * @param sendLongRequest The sendLongRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendLongWithResponseAsync(String name, BinaryData sendLongRequest,
        RequestOptions requestOptions) {
        final String contentType = "application/json";
        return FluxUtil.withContext(context -> service.sendLong(this.getEndpoint(), name,
            this.getServiceVersion().getVersion(), contentType, sendLongRequest, requestOptions, context));
    }

    /**
     * The sendLong operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     dataInt: int (Required)
     *     dataIntOptional: Integer (Optional)
     *     dataLong: Long (Optional)
     *     requiredUser (Required): (recursive schema, see requiredUser above)
     *     data_float: Double (Optional)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     _dummy: String (Optional)
     *     constant: String (Required)
     * }
     * }</pre>
     * 
     * @param name The name parameter.
     * @param sendLongRequest The sendLongRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendLongWithResponse(String name, BinaryData sendLongRequest, RequestOptions requestOptions) {
        final String contentType = "application/json";
        return service.sendLongSync(this.getEndpoint(), name, this.getServiceVersion().getVersion(), contentType,
            sendLongRequest, requestOptions, Context.NONE);
    }

    /**
     * The update operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     patch (Optional, Required on create): {
     *         title: String (Optional)
     *         description: String (Optional)
     *         status: String(NotStarted/InProgress/Completed) (Optional)
     *     }
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: long (Required)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     createdAt: OffsetDateTime (Required)
     *     updatedAt: OffsetDateTime (Required)
     *     completedAt: OffsetDateTime (Optional)
     *     _dummy: String (Optional)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param updateRequest The updateRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> updateWithResponseAsync(long id, BinaryData updateRequest,
        RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.update(this.getEndpoint(), contentType, id, accept,
            updateRequest, requestOptions, context));
    }

    /**
     * The update operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     patch (Optional, Required on create): {
     *         title: String (Optional)
     *         description: String (Optional)
     *         status: String(NotStarted/InProgress/Completed) (Optional)
     *     }
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: long (Required)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     createdAt: OffsetDateTime (Required)
     *     updatedAt: OffsetDateTime (Required)
     *     completedAt: OffsetDateTime (Optional)
     *     _dummy: String (Optional)
     * }
     * }</pre>
     * 
     * @param id The id parameter.
     * @param updateRequest The updateRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> updateWithResponse(long id, BinaryData updateRequest, RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return service.updateSync(this.getEndpoint(), contentType, id, accept, updateRequest, requestOptions,
            Context.NONE);
    }

    /**
     * The uploadFile operation.
     * 
     * @param name The name parameter.
     * @param uploadFileRequest The uploadFileRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadFileWithResponseAsync(String name, BinaryData uploadFileRequest,
        RequestOptions requestOptions) {
        final String contentType = "multipart/form-data";
        return FluxUtil.withContext(context -> service.uploadFile(this.getEndpoint(), name, contentType,
            uploadFileRequest, requestOptions, context));
    }

    /**
     * The uploadFile operation.
     * 
     * @param name The name parameter.
     * @param uploadFileRequest The uploadFileRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadFileWithResponse(String name, BinaryData uploadFileRequest,
        RequestOptions requestOptions) {
        final String contentType = "multipart/form-data";
        return service.uploadFileSync(this.getEndpoint(), name, contentType, uploadFileRequest, requestOptions,
            Context.NONE);
    }

    /**
     * The uploadTodo operation.
     * 
     * @param uploadTodoRequest The uploadTodoRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadTodoWithResponseAsync(BinaryData uploadTodoRequest,
        RequestOptions requestOptions) {
        final String contentType = "multipart/form-data";
        return FluxUtil.withContext(
            context -> service.uploadTodo(this.getEndpoint(), contentType, uploadTodoRequest, requestOptions, context));
    }

    /**
     * The uploadTodo operation.
     * 
     * @param uploadTodoRequest The uploadTodoRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadTodoWithResponse(BinaryData uploadTodoRequest, RequestOptions requestOptions) {
        final String contentType = "multipart/form-data";
        return service.uploadTodoSync(this.getEndpoint(), contentType, uploadTodoRequest, requestOptions, Context.NONE);
    }
}