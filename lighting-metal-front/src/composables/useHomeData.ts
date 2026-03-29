// 🔥 修复：正确具名导出 useHomeData
export function useHomeData() {
    const router = useRouter()

    // 跳转登录
    const goLogin = () => router.push('/login')
    // 跳转注册
    const goRegister = () => router.push('/register')

    // 商品分类
    const categoryList = [
        { id: 1, name: '五金零件', desc: '螺丝/螺母/垫片/标准件' },
        { id: 2, name: '五金工具', desc: '手动/电动/气动工具' },
        { id: 3, name: '紧固件', desc: '螺栓/膨胀螺丝/铆钉' },
        { id: 4, name: '五金配件', desc: '门窗/家具/机械配件' }
    ]

    // 商户+商品+生产线
    const merchantList = [
        {
            id: 1,
            name: '鑫源五金精密制造厂',
            logo: 'https://picsum.photos/80/80?random=1',
            intro: '专注不锈钢五金零件生产15年，国标/非标定制均可，日产零件50万件，资质齐全',
            production: [
                'https://picsum.photos/seed/xinyuan-line1/400/300',
                'https://picsum.photos/seed/xinyuan-line2/400/300'
            ],
            goods: [
                { id: 101, name: '304不锈钢十字螺丝', price: '¥0.1/个', tag: '爆款' },
                { id: 102, name: '六角加厚螺母', price: '¥0.08/个', tag: '热销' },
                { id: 103, name: '平垫片/弹垫片', price: '¥0.05/个', tag: '现货' },
                { id: 104, name: '非标车削零件', price: '¥0.8/个', tag: '定制' }
            ]
        },
        {
            id: 2,
            name: '得力工业工具旗舰店',
            logo: 'https://picsum.photos/80/80?random=2',
            intro: '知名工具品牌授权经销商，全系列手动/电动工具，工程专供，量大价优',
            production: [
                'https://picsum.photos/seed/deli-tool1/400/300',
                'https://picsum.photos/seed/deli-tool2/400/300'
            ],
            goods: [
                { id: 201, name: '防滑十字螺丝刀', price: '¥5.9/把', tag: '爆款' },
                { id: 202, name: '多功能活动扳手', price: '¥12.9/把', tag: '热销' },
                { id: 203, name: '工业级钢丝钳', price: '¥18.9/把', tag: '现货' },
                { id: 204, name: '充电式手电钻', price: '¥199/台', tag: '新品' }
            ]
        }
    ]

    const productionImages = [
        'https://picsum.photos/400/300?random=1',
        'https://picsum.photos/400/300?random=2',
        'https://picsum.photos/400/300?random=3'
    ]

    return {
        goLogin,
        goRegister,
        categoryList,
        merchantList,
        productionImages
    }
}

// 🔥 补充缺失的路由导入
import { useRouter } from 'vue-router'