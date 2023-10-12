n,m,k = list(map(int,input().split()))
arr = []
for i in range(n):
    arr.append(list(map(int,input().split())))
states = [[0]*n for i in range(n)]
parts = []
for i in range(m):
    px,py = list(map(int,input().split()))
    px -= 1; py -= 1;
    states[px][py] = states[px][py] | (1<<i)
    parts.append([px,py])
ex,ey = list(map(int,input().split()))
ex -= 1; ey -= 1;
states[ex][ey] = -1
dx = [-1,1,0,0]
dy = [0,0,1,-1]
ans = 0
out = 0
def rotate(x,y,d):
    global ex,ey
    subarr = [[0]*d for i in range(d)]
    substates = [[0]*d for i in range(d)]
    for i in range(d):
        for j in range(d):
            subarr[i][j] = arr[x+d-1-j][y+i]
            substates[i][j] = states[x+d-1-j][y+i]
            if subarr[i][j] >= 1: subarr[i][j] -= 1
            if substates[i][j] >= 1:
                for k in range(m):
                    if substates[i][j] & (1<<k) > 0:
                        parts[k] = [x+i, y+j]
            if substates[i][j] == -1:
                ex = x + i; ey = y + j;
    for i in range(d):
        for j in range(d):
            arr[x+i][y+j] = subarr[i][j]
            states[x+i][y+j] = substates[i][j]
def getDistToExit(x1,y1):
    return abs(x1-ex) + abs(y1-ey)
def move():
    global ans,out
    for i in range(len(parts)):
        px,py = parts[i]
        if px == -1 and py == -1: continue
        cur_dist = getDistToExit(px,py)
        for k in range(4):
            nx = px + dx[k]
            ny = py + dy[k]
            if nx < 0 or nx >= n or ny < 0 or ny >= n or arr[nx][ny] != 0: continue
            next_dist = getDistToExit(nx,ny)
            if cur_dist <= next_dist: continue
            # 움직이는 경우
            ans += 1
            states[px][py] ^= (1<<i)
            if nx == ex and ny == ey:
                parts[i] = [-1,-1]
                out += 1
            else:
                states[nx][ny] |= (1<<i)
                parts[i] = [nx,ny]
            break

def getRect():
    for d in range(1,n+1):
        for i in range(ex-d,ex+1):
            for j in range(ey-d,ey+1):
                # 시작점 i,j
                # print(i,j,d)
                if i < 0 or j < 0 or i+d >= n or j + d >= n: continue
                for ii in range(i,i+d+1):
                    for jj in range(j,j+d+1):
                        if states[ii][jj] >= 1:
                            return [i,j,d+1]
    return []
for i in range(k):
    move()
    if out == m:
        break
    result = getRect()
    x,y,d = result
    rotate(x,y,d)

print(ans)
print(ex+1,ey+1)
