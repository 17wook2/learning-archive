v = int(input())
graph = [[] for i in range(v+1)]
visited = [0]*(v+1)
for i in range(v):
    row = list(map(int,input().split()))
    x = row[0]
    for j in range(1,len(row)-1,2):
        node = row[j]
        dist = row[j+1]
        graph[x].append([node, dist])
ans = 0
def dfs(idx, dis):
    global ans
    visited[idx] = 1
    midx = idx
    mdis = dis
    for nd in graph[idx]:
        node = nd[0]
        dist = nd[1]
        if not visited[node]:
            res = dfs(node, dis + dist)
            if mdis <= res[1]:
                midx = res[0]
                mdis = res[1]
    return [midx,mdis]

res = dfs(1,0)
visited = [0]*(v+1)
res2 = dfs(res[0],0)
print(res2[1])
