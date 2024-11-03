package org.mobilenativefoundation.trails.xplat.lib.repositories.post.api

import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.operations.delete.DeleteAllOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.delete.DeleteManyOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.delete.DeleteOneOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.QueryManyCompositeOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.QueryManyOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.query.QueryOneOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.read.*
import org.mobilenativefoundation.trails.xplat.lib.operations.update.UpdateOneOperation
import org.mobilenativefoundation.trails.xplat.lib.operations.upsert.UpsertOneOperation

interface PostRepository :
    FindOneOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    FindOneCompositeOperation<Post.Key, Post.Properties, Post.Edges, Post.Composite>,
    FindManyOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    FindAllOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    ObserveOneOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    UpdateOneOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    UpsertOneOperation<Post.Key, Post.Properties>,
    QueryOneOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    QueryManyOperation<Post.Key, Post.Properties, Post.Edges, Post.Node>,
    QueryManyCompositeOperation<Post.Key, Post.Properties, Post.Edges, Post.Node, Post.Composite>,
    DeleteOneOperation<Post.Key>,
    DeleteManyOperation<Post.Key>,
    DeleteAllOperation