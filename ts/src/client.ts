import createClient, { type Middleware } from "openapi-fetch";
import type { paths as AuthPaths } from "../auth-api.js";
import type { paths as RelayPaths } from "../relay-api.js";
import type { paths as BlobPaths } from "../blob-api.js";
import type { paths as OrgPaths } from "../org-api.js";
import type { paths as KeyauthPaths } from "../keyauth-api.js";
import type { paths as AuditchainPaths } from "../auditchain-api.js";

/**
 * Error thrown when an API request returns a non-2xx status.
 */
export class ApiError extends Error {
  constructor(
    public readonly status: number,
    public readonly statusText: string,
    public readonly body?: unknown,
  ) {
    super(`API Error: ${status} ${statusText}`);
    this.name = "ApiError";
  }
}

/**
 * Middleware that throws {@link ApiError} on non-2xx responses.
 */
export const throwOnError: Middleware = {
  async onResponse({ response }) {
    if (!response.ok) {
      const body = await response.json().catch(() => undefined);
      throw new ApiError(response.status, response.statusText, body);
    }
    return response;
  },
};

export interface ClientOptions {
  baseUrl?: string;
  credentials?: RequestCredentials;
}

const defaults: Required<ClientOptions> = {
  baseUrl: "",
  credentials: "include",
};

function makeClient<Paths extends {}>(options?: ClientOptions) {
  const client = createClient<Paths>({
    baseUrl: options?.baseUrl ?? defaults.baseUrl,
    credentials: options?.credentials ?? defaults.credentials,
  });
  client.use(throwOnError);
  return client;
}

export function createAuthClient(options?: ClientOptions) {
  return makeClient<AuthPaths>(options);
}

export function createRelayClient(options?: ClientOptions) {
  return makeClient<RelayPaths>(options);
}

export function createBlobClient(options?: ClientOptions) {
  return makeClient<BlobPaths>(options);
}

export function createOrgClient(options?: ClientOptions) {
  return makeClient<OrgPaths>(options);
}

export function createKeyauthClient(options?: ClientOptions) {
  return makeClient<KeyauthPaths>(options);
}

export function createAuditchainClient(options?: ClientOptions) {
  return makeClient<AuditchainPaths>(options);
}

export type AuthClient = ReturnType<typeof createAuthClient>;
export type RelayClient = ReturnType<typeof createRelayClient>;
export type BlobClient = ReturnType<typeof createBlobClient>;
export type OrgClient = ReturnType<typeof createOrgClient>;
export type KeyauthClient = ReturnType<typeof createKeyauthClient>;
export type AuditchainClient = ReturnType<typeof createAuditchainClient>;

/**
 * Unwraps an openapi-fetch response, throwing if the request failed.
 */
export function unwrapApiResponse<TData>(
  result: { data?: TData; error?: unknown },
  errorMessage: string,
): TData {
  if (result.error || result.data === undefined) {
    throw new Error(errorMessage);
  }
  return result.data;
}
