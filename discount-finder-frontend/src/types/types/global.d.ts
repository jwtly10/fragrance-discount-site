declare type Product = {
    url: string
    brand: string
    itemName: string
    currentPrice: number
    type: string
    oldPrice: number
    saving: number
    discount: number
    imageUrl: string
    inStock: boolean
    size: string
    site: Site
}

declare type Site = {
    name: string
    url: string
}
