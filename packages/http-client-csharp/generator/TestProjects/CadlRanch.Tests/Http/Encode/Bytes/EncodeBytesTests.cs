// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

using System;
using System.ClientModel;
using System.IO;
using System.Threading.Tasks;
using Encode.Bytes;
using Encode.Bytes.Models;
using NUnit.Framework;

namespace TestProjects.CadlRanch.Tests.Http.Encode.Bytes
{
    public class EncodeBytesTests : CadlRanchTestBase
    {
        private string SamplePngPath = Path.Combine(CadlRanchServer.GetSpecDirectory(), "assets", "image.png");

        [CadlRanchTest]
        public Task QueryDefault() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");

            ClientResult result = await new BytesClient(host, null).GetQueryClient().DefaultAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task QueryBase64() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetQueryClient().Base64Async(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task QueryBase64url() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetQueryClient().Base64urlAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task QueryBase64urlArray() => Test(async (host) =>
        {
            BinaryData data1 = BinaryData.FromString("test");
            BinaryData data2 = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetQueryClient().Base64urlArrayAsync(new[] { data1, data2 });
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task PropertyDefault() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            var body = new DefaultBytesProperty(data);
            DefaultBytesProperty response = await new BytesClient(host, null).GetPropertyClient().DefaultAsync(body);
            BinaryDataAssert.AreEqual(body.Value, response.Value);
        });

        [CadlRanchTest]
        public Task PropertyBase64() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            var body = new Base64BytesProperty(data);
            Base64BytesProperty response = await new BytesClient(host, null).GetPropertyClient().Base64Async(body);
            BinaryDataAssert.AreEqual(body.Value, response.Value);
        });

        [CadlRanchTest]
        public Task PropertyBase64url() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            var body = new Base64urlBytesProperty(data);
            Base64urlBytesProperty response = await new BytesClient(host, null).GetPropertyClient().Base64urlAsync(body);
            BinaryDataAssert.AreEqual(body.Value, response.Value);
        });

        [CadlRanchTest]
        public Task Base64urlArrayBytesProperty() => Test(async (host) =>
        {
            BinaryData data1 = BinaryData.FromString("test");
            BinaryData data2 = BinaryData.FromString("test");
            var body = new Base64urlArrayBytesProperty(new[] {data1,data2});
            Base64urlArrayBytesProperty response = await new BytesClient(host, null).GetPropertyClient().Base64urlArrayAsync(body);
            BinaryDataAssert.AreEqual(body.Value[0], response.Value[0]);
            BinaryDataAssert.AreEqual(body.Value[1], response.Value[1]);
        });

        [CadlRanchTest]
        public Task HeaderDefault() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");

            ClientResult result = await new BytesClient(host, null).GetHeaderClient().DefaultAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task HeaderBase64() => Test(async (host) =>
        {

            BinaryData data = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetHeaderClient().Base64Async(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task HeaderBase64url() => Test(async (host) =>
        {
            BinaryData data = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetHeaderClient().Base64urlAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task HeaderBase64urlArray() => Test(async (host) =>
        {
            BinaryData data1 = BinaryData.FromString("test");
            BinaryData data2 = BinaryData.FromString("test");
            ClientResult result = await new BytesClient(host, null).GetHeaderClient().Base64urlArrayAsync(new[] { data1, data2 });
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task RequestBodyDefault() => Test(async (host) =>
        {
            BinaryData data = new BinaryData($"\"{Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes("test"))}\"");
            ClientResult result = await new BytesClient(host, null).GetRequestBodyClient().DefaultAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task RequestBodyOctetStream() => Test(async (host) =>
        {
            BinaryData data = new BinaryData(File.ReadAllBytes(SamplePngPath));
            ClientResult result = await new BytesClient(host, null).GetRequestBodyClient().OctetStreamAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task RequestBodyCustomContentType() => Test(async (host) =>
        {
            BinaryData data = new BinaryData(File.ReadAllBytes(SamplePngPath));
            ClientResult result = await new BytesClient(host, null).GetRequestBodyClient().CustomContentTypeAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task RequestBodyBase64() => Test(async (host) =>
        {
            BinaryData data = new BinaryData($"\"{Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes("test"))}\"");
            ClientResult result = await new BytesClient(host, null).GetRequestBodyClient().Base64Async(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task RequestBodyBase64url() => Test(async (host) =>
        {
            BinaryData data = new BinaryData($"\"{Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes("test")).Replace('+', '-').Replace('/', '_').Replace("=", "")}\"");
            ClientResult result = await new BytesClient(host, null).GetRequestBodyClient().Base64urlAsync(data);
            Assert.AreEqual(204, result.GetRawResponse().Status);
        });

        [CadlRanchTest]
        public Task ResponseBodyDefault() => Test(async (host) =>
        {
            var response = await new BytesClient(host, null).GetResponseBodyClient().DefaultAsync();
            CollectionAssert.AreEqual(BinaryData.FromObjectAsJson("dGVzdA==").ToArray(), response.Value.ToArray());
        });

        [CadlRanchTest]
        public Task ResponseBodyOctetStream() => Test(async (host) =>
        {
            BinaryData data = new BinaryData(File.ReadAllBytes(SamplePngPath));
            var response = await new BytesClient(host, null).GetResponseBodyClient().OctetStreamAsync();
            CollectionAssert.AreEqual(data.ToArray(), response.Value.ToArray());
        });

        [CadlRanchTest]
        public Task ResponseBodyCustomContentType() => Test(async (host) =>
        {
            BinaryData data = new BinaryData(File.ReadAllBytes(SamplePngPath));
            BinaryData result = await new BytesClient(host, null).GetResponseBodyClient().CustomContentTypeAsync();
            BinaryDataAssert.AreEqual(data, result);
        });

        [CadlRanchTest]
        public Task ResponseBodyBase64() => Test(async (host) =>
        {
            BinaryData result = await new BytesClient(host, null).GetResponseBodyClient().Base64Async();
            BinaryDataAssert.AreEqual(BinaryData.FromObjectAsJson("dGVzdA=="), result);
        });

        [CadlRanchTest]
        public Task ResponseBodyBase64url() => Test(async (host) =>
        {
            BinaryData result = await new BytesClient(host, null).GetResponseBodyClient().Base64urlAsync();
            BinaryDataAssert.AreEqual(BinaryData.FromObjectAsJson("dGVzdA"), result);
        });
    }
}